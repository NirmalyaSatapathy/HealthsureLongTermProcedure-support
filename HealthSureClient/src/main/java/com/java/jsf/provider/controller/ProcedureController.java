package com.java.jsf.provider.controller;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.java.ejb.provider.bean.ProviderEjbImpl;
import com.java.ejb.provider.model.Doctor;
import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.MedicineType;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureStatus;
import com.java.ejb.provider.model.ProcedureTest;
import com.java.ejb.provider.model.ProcedureType;
import com.java.ejb.provider.model.Provider;
import com.java.ejb.recipient.model.Recipient;
import com.java.jsf.provider.daoImpl.ProviderDaoImpl;
import com.java.ejb.provider.model.ProcedureDailyLog;
import com.java.jsf.util.ProcedureIdGenerator;
import com.java.ejb.provider.model.Appointment;

public class ProcedureController {
    private ProviderEjbImpl providerEjb;
    private ProviderDaoImpl providerDao;
    MedicalProcedure procedure;
    Prescription prescription;
    PrescribedMedicines prescribedMedicines;
    ProcedureTest procedureTest;
    ProcedureDailyLog procedureLog;
    private String procedureType; // "single" or "scheduled"
    MedicalProcedure selectedProcedure;
    public String getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public ProcedureController() {
        super();
    }

    public ProviderEjbImpl getProviderEjb() {
        return providerEjb;
    }

    public void setProviderEjb(ProviderEjbImpl providerEjb) {
        this.providerEjb = providerEjb;
    }

    public ProviderDaoImpl getProviderDao() {
        return providerDao;
    }

    public void setProviderDao(ProviderDaoImpl providerDao) {
        this.providerDao = providerDao;
    }
    public String addSingleDayMedicalProcedureController(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        // Validate Recipient Existence
        Recipient recipient = providerDao.searchRecipientByHealthId(medicalProcedure.getRecipient().gethId());
        if (recipient == null) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Provider Existence
        Provider provider = providerDao.searchProviderById(medicalProcedure.getProvider().getProviderId());
        if (provider == null) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Doctor Existence
        Doctor doctor = providerDao.searchDoctorById(medicalProcedure.getDoctor().getDoctorId());
        if (doctor == null) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Appointment Existence and Association
        Appointment appointment = providerDao.searchAppointmentById(medicalProcedure.getAppointment().getAppointmentId());
        if (appointment == null) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equals(medicalProcedure.getProvider().getProviderId())) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getDoctor().getDoctorId().equals(medicalProcedure.getDoctor().getDoctorId())) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getRecipient().gethId().equals(medicalProcedure.getRecipient().gethId())) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        // Procedure Date Validation
        Date procedureDate = medicalProcedure.getProcedureDate();
        Date today = new Date();
        if (procedureDate == null) {
            context.addMessage("procedureDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Missing Date", "Procedure Date is required."));
            context.validationFailed();
            isValid = false;
        } else if (procedureDate.after(today)) {
            context.addMessage("procedureDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Date", "Procedure Date cannot be in the future."));
            context.validationFailed();
            isValid = false;
        }

        if (!isValid) {
            return null;
        }

        // Set single-day specific properties
        medicalProcedure.setFromDate(null);
        medicalProcedure.setToDate(null);
        medicalProcedure.setScheduledDate(null);
        medicalProcedure.setType(ProcedureType.SINGLE_DAY);
        medicalProcedure.setProcedureStatus(ProcedureStatus.COMPLETED);
        // Persist using EJB
        String res = providerEjb.addMedicalProcedure(medicalProcedure);
        this.procedure = null;
        return res;
    }
    public String addScheduledMedicalProcedureController(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        // Validate Recipient Existence
        Recipient recipient = providerDao.searchRecipientByHealthId(medicalProcedure.getRecipient().gethId());
        if (recipient == null) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Provider Existence
        Provider provider = providerDao.searchProviderById(medicalProcedure.getProvider().getProviderId());
        if (provider == null) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Doctor Existence
        Doctor doctor = providerDao.searchDoctorById(medicalProcedure.getDoctor().getDoctorId());
        if (doctor == null) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Appointment Existence and Association
        Appointment appointment = providerDao.searchAppointmentById(medicalProcedure.getAppointment().getAppointmentId());
        if (appointment == null) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equals(medicalProcedure.getProvider().getProviderId())) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getDoctor().getDoctorId().equals(medicalProcedure.getDoctor().getDoctorId())) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getRecipient().gethId().equals(medicalProcedure.getRecipient().gethId())) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        // Validate Scheduled Date (must be in future)
        Date scheduledDate = medicalProcedure.getScheduledDate();
        Date today = new Date();
        if (scheduledDate == null) {
            context.addMessage("scheduledDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Missing Date", "Scheduled Date is required."));
            context.validationFailed();
            isValid = false;
        } else if (!scheduledDate.after(today)) {
            context.addMessage("scheduledDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Date", "Scheduled Date must be in the future."));
            context.validationFailed();
            isValid = false;
        }

        if (!isValid) {
            return null;
        }

        // Set Scheduled Procedure-specific values
        medicalProcedure.setProcedureDate(null); // Not yet performed
        medicalProcedure.setFromDate(null);
        medicalProcedure.setToDate(null);
        medicalProcedure.setType(ProcedureType.LONG_TERM); // Still single-day
        medicalProcedure.setProcedureStatus(ProcedureStatus.SCHEDULED);
        // Persist using EJB
        String res = providerEjb.addMedicalProcedure(medicalProcedure);
        this.procedure = null;
        return res;
    }
    public String addInProgressMedicalProcedureController(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        // Validate Recipient
        Recipient recipient = providerDao.searchRecipientByHealthId(medicalProcedure.getRecipient().gethId());
        if (recipient == null) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Provider
        Provider provider = providerDao.searchProviderById(medicalProcedure.getProvider().getProviderId());
        if (provider == null) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Doctor
        Doctor doctor = providerDao.searchDoctorById(medicalProcedure.getDoctor().getDoctorId());
        if (doctor == null) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        // Validate Appointment
        Appointment appointment = providerDao.searchAppointmentById(medicalProcedure.getAppointment().getAppointmentId());
        if (appointment == null) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equals(medicalProcedure.getProvider().getProviderId())) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getDoctor().getDoctorId().equals(medicalProcedure.getDoctor().getDoctorId())) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }

            if (!appointment.getRecipient().gethId().equals(medicalProcedure.getRecipient().gethId())) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        if (!isValid) {
            return null;
        }

        // Set all irrelevant date fields to null explicitly
        medicalProcedure.setScheduledDate(null);      // not used for in-progress
        medicalProcedure.setProcedureDate(null);      // not used for in-progress
        medicalProcedure.setToDate(null);             // optional, could be set later if long-term
        medicalProcedure.setType(ProcedureType.LONG_TERM); // Still single-day
        medicalProcedure.setProcedureStatus(ProcedureStatus.IN_PROGRESS);
        String res = providerEjb.addMedicalProcedure(medicalProcedure);
        this.procedure = null;
        return res;
    }

    public String addTestController(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();

        String prescriptionId = (String) sessionMap.get("prescriptionId");
        procedureTest.getPrescription().setPrescriptionId(prescriptionId);

        // Fetch prescription writtenOn and procedure endDate
        Date writtenOn = providerDao.getPrescriptionWrittenOnDate(prescriptionId);
        String procedureId = (String) sessionMap.get("procedureId"); // assume stored in session
        Date procedureEndDate = providerDao.getProcedureEndDate(procedureId);

        // 1. Validate Test Name
        String testName = procedureTest.getTestName();
        if (testName == null || testName.trim().length() < 2 || !testName.matches("^[a-zA-Z0-9 ()/\\-.]+$")) {
            context.addMessage("testName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Test name must be at least 2 characters and contain only letters, numbers, spaces, (), /, -, and .", null));
            return null;
        }
        testName = testName.trim().replaceAll("\\s+", " ");
        procedureTest.setTestName(testName);

     // 2. Validate Test Date
        Date testDate = procedureTest.getTestDate();
        if (testDate == null) {
            context.addMessage("testDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Test date is required.", null));
            return null;
        }

        // Get prescription start and end dates
        List<Date> prescriptionDates = providerDao.getPrescriptionDates(prescriptionId);
        if (prescriptionDates == null || prescriptionDates.size() != 2 ||
            prescriptionDates.get(0) == null || prescriptionDates.get(1) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Could not retrieve valid prescription dates for test date validation.", null));
            return null;
        }

        Date prescriptionStart = prescriptionDates.get(0);
        Date prescriptionEnd = prescriptionDates.get(1);

        if (testDate.before(prescriptionStart)) {
            context.addMessage("testDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Test date (" + testDate + ") cannot be before prescription start date (" + prescriptionStart + ").", null));
            return null;
        }

        if (testDate.after(prescriptionEnd)) {
            context.addMessage("testDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Test date (" + testDate + ") cannot be after prescription end date (" + prescriptionEnd + ").", null));
            return null;
        }


        // 3. Validate Result Summary
        String result = procedureTest.getResultSummary();
        if (result == null || result.trim().isEmpty()) {
            context.addMessage("resultSummary", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Result summary is required.", null));
            return null;
        }

        return providerEjb.addTest(procedureTest);
    }



    public String addPresribedMedicinesController(PrescribedMedicines prescribedMedicines)
            throws ClassNotFoundException, SQLException {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();

        String prescriptionId = (String) sessionMap.get("prescriptionId");
        prescribedMedicines.getPrescription().setPrescriptionId(prescriptionId);

        // 1. Medicine Name Validation
        String medicineName = prescribedMedicines.getMedicineName();
        if (medicineName == null || !medicineName.matches("^[a-zA-Z0-9()\\-+/'. ]{2,50}$")) {
            context.addMessage("medicineName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Medicine name must be 2–50 characters and can include letters, digits, -, /, +, (), '.', and spaces.", null));
            return null;
        }

        // Normalize medicine name
        medicineName = medicineName.trim().replaceAll("\\s+", " ");
        prescribedMedicines.setMedicineName(medicineName);

        // 2. Check for duplicate medicine in same prescription
        List<String> existingMedicineNames = providerDao.getMedicineNamesByPrescriptionId(prescriptionId);
        for (String existingName : existingMedicineNames) {
            if (existingName.equalsIgnoreCase(medicineName)) {
                context.addMessage("medicineName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "This medicine is already prescribed in this prescription.", null));
                return null;
            }
        }

        // 3. Dosage Validation
        String dosage = prescribedMedicines.getDosage();
        if (dosage == null || dosage.trim().isEmpty()) {
            context.addMessage("dosage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dosage is required.", null));
            return null;
        }

        MedicineType type = prescribedMedicines.getType();
        String pattern;

        switch (type) {
            case TABLET:
                pattern = "^\\d+\\s*tablet(s)?$";
                break;
            case SYRUP:
                pattern = "^\\d+(\\.\\d+)?\\s*ml$";
                break;
            case INJECTION:
                pattern = "^(\\d+(\\.\\d+)?\\s*ml|\\d+\\s*dose(s)?)$";
                break;
            case DROP:
                pattern = "^\\d+\\s*drop(s)?$";
                break;
            default:
                context.addMessage("type", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Invalid or missing medicine type.", null));
                return null;
        }

        if (!dosage.trim().toLowerCase().matches(pattern)) {
            context.addMessage("dosage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dosage format is invalid for type: " + type, null));
            return null;
        }

        // 4. Fetch Prescription Dates
        List<Date> prescriptionDates = providerDao.getPrescriptionDates(prescriptionId);
        if (prescriptionDates == null || prescriptionDates.size() != 2 ||
            prescriptionDates.get(0) == null || prescriptionDates.get(1) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Could not retrieve valid prescription dates for validation.", null));
            return null;
        }

        Date prescriptionStart = prescriptionDates.get(0);
        Date prescriptionEnd = prescriptionDates.get(1);
        Date medStart = prescribedMedicines.getStartDate();
        Date medEnd = prescribedMedicines.getEndDate();

     // 5. Validate Medicine Date Range
        if (medStart == null) {
            context.addMessage("startDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Enter from which date to start taking medicine", null));
            return null;
        }

        if (medEnd == null) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Enter till which date to take the medicines", null));
            return null;
        }

        if (medEnd.before(medStart)) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "End date (" + medEnd + ") cannot be before start date (" + medStart + ").", null));
            return null;
        }

        if (medStart.before(prescriptionStart)) {
            context.addMessage("startDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Start date (" + medStart + ") cannot be before prescription start date (" + prescriptionStart + ").", null));
            return null;
        }

        if (medStart.after(prescriptionEnd)) {
            context.addMessage("startDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Start date (" + medStart + ") cannot be after prescription end date (" + prescriptionEnd + ").", null));
            return null;
        }

        if (medEnd.before(prescriptionStart)) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "End date (" + medEnd + ") cannot be before prescription start date (" + prescriptionStart + ").", null));
            return null;
        }

        if (medEnd.after(prescriptionEnd)) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "End date (" + medEnd + ") cannot be after prescription end date (" + prescriptionEnd + ").", null));
            return null;
        }
        // 6. Duration Validation
        long dayDiff = (medEnd.getTime() - medStart.getTime()) / (1000 * 60 * 60 * 24) + 1;

        try {
            int durationDays = Integer.parseInt(prescribedMedicines.getDuration().trim());

            if (durationDays != dayDiff) {
                context.addMessage("duration", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Duration (" + durationDays + " days) does not match actual period (" + dayDiff + " days).", null));
                return null;
            }
        } catch (NumberFormatException e) {
            context.addMessage("duration", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Duration must be a valid integer number.", null));
            return null;
        }

        // ✅ All validations passed
        return providerEjb.addPrescribedMedicines(prescribedMedicines);
    }

    public String addPrescriptionController(Prescription prescription) throws ClassNotFoundException, SQLException {
    	System.out.println("prescription controller called");
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();

        // Set reference entities
        MedicalProcedure procedure = new MedicalProcedure();
        String procedureId = (String) sessionMap.get("procedureId");
        procedure.setProcedureId(procedureId);
        prescription.setProcedure(procedure);

        Provider provider = new Provider();
        provider.setProviderId((String) sessionMap.get("providerId"));
        prescription.setProvider(provider);

        Doctor doctor = new Doctor();
        doctor.setDoctorId((String) sessionMap.get("doctorId"));
        prescription.setDoctor(doctor);

        Recipient recipient = new Recipient();
        recipient.sethId((String) sessionMap.get("recipientHid"));
        prescription.setRecipient(recipient);

        // Validate writtenOn field
        if (prescription.getWrittenOn() == null) {
            context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Please enter the Written On date.", null));
            return null;
        }

        // ✅ Fetch procedure details
        ProcedureType procedureType = (ProcedureType) sessionMap.get("procedureType");
        ProcedureStatus procedureStatus = (ProcedureStatus) sessionMap.get("procedureStatus");

        Date procedureDate = (Date) sessionMap.get("procedureDate"); // for SINGLE_DAY
        Date fromDate = (Date) sessionMap.get("fromDate");           // for LONG_TERM + IN_PROGRESS

        if (procedureType == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Missing Procedure Type for Procedure ID: " + procedureId, null));
            return null;
        }

        // ✅ SINGLE_DAY: writtenOn must be equal to procedureDate
        if (procedureType == ProcedureType.SINGLE_DAY) {
            if (procedureDate == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Missing Procedure Date for SINGLE_DAY procedure ID: " + procedureId, null));
                return null;
            }
            if (!prescription.getWrittenOn().equals(procedureDate)) {
                context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Written On date (" + prescription.getWrittenOn() +
                                ") must exactly match the Procedure Date (" + procedureDate + ").", null));
                return null;
            }
        }
        System.out.println(fromDate);
        System.out.println(prescription.getWrittenOn());
        // ✅ LONG_TERM + IN_PROGRESS: writtenOn must be after fromDate
        System.out.println("reached for validation of longterm in progress");
        if (procedureType == ProcedureType.LONG_TERM &&
            procedureStatus == ProcedureStatus.IN_PROGRESS) {

            if (fromDate == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Missing From Date for LONG_TERM procedure ID: " + procedureId, null));
                return null;
            }

            if (!prescription.getWrittenOn().after(fromDate)) {
                context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Written On date (" + prescription.getWrittenOn() +
                                ") must be after the Procedure From Date (" + fromDate + ") for long-term in-progress procedures.", null));
                return null;
            }
        }

        // Validate prescription start and end dates
        if (prescription.getStartDate().before(prescription.getWrittenOn())) {
            context.addMessage("startDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Prescription start date (" + prescription.getStartDate() +
                            ") cannot be before the prescription written date (" + prescription.getWrittenOn() + ").", null));
            return null;
        }

        if (prescription.getEndDate().before(prescription.getStartDate())) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Prescription end date (" + prescription.getEndDate() +
                            ") cannot be before the prescription start date (" + prescription.getStartDate() + ").", null));
            return null;
        }

        return providerEjb.addPrescription(prescription);
    }
    public String addProcedureLogController(ProcedureDailyLog procedureLog) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();

        String procedureId = (String) sessionMap.get("procedureId");
        procedureLog.getMedicalProcedure().setProcedureId(procedureId);

        // 1. Validate Log Date
        Date logDate = procedureLog.getLogDate();
        if (logDate == null) {
            context.addMessage("logDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Log date is required.", null));
            return null;
        }

        // Get procedure fromDate
        Date fromDate = (Date) sessionMap.get("fromDate");
        if (fromDate == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Unable to fetch procedure's start date for validation.", null));
            return null;
        }

        if (logDate.before(fromDate)) {
            context.addMessage("logDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Log date (" + logDate + ") cannot be before procedure start date (" + fromDate + ").", null));
            return null;
        }

        // 2. Validate (optional) vitals
        String vitals = procedureLog.getVitals();
        if (vitals != null && !vitals.trim().isEmpty()) {
            vitals = vitals.trim().replaceAll("\\s+", " ");
            if (vitals.length() > 300) {
                context.addMessage("vitals", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Vitals should not exceed 300 characters.", null));
                return null;
            }
            procedureLog.setVitals(vitals);
        }

        // 3. Validate Notes
        String notes = procedureLog.getNotes();
        if (notes == null || notes.trim().isEmpty()) {
            context.addMessage("notes", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Notes are required.", null));
            return null;
        }
        notes = notes.trim().replaceAll("\\s+", " ");
        if (notes.length() > 1000) {
            context.addMessage("notes", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Notes should not exceed 1000 characters.", null));
            return null;
        }
        procedureLog.setNotes(notes);

        // 4. Set createdAt
        procedureLog.setCreatedAt(new Date());

        // 5. Call EJB layer
        return providerEjb.addProcedureLog(procedureLog);
    }

    public String createNewProcedure() throws ClassNotFoundException, SQLException {
        procedure = new MedicalProcedure();
        procedure.setProcedureId(providerEjb.generateNewProcedureId());

        // Get today's date
        Date today = new Date(); // java.util.Date

        String nextPage = null;

        if ("single".equalsIgnoreCase(procedureType)) {
            procedure.setProcedureDate(today); // Single-day procedure done today
            procedure.setProcedureStatus(ProcedureStatus.COMPLETED);
            nextPage = "AddMedicalProcedure?faces-redirect=true";

        } else if ("scheduled".equalsIgnoreCase(procedureType)) {
            // No date set here. Will be chosen by user in future.
            nextPage = "AddScheduledMedicalProcedure?faces-redirect=true";

        } else if ("inprogress".equalsIgnoreCase(procedureType)) {
            procedure.setFromDate(today); // In-progress begins today
            procedure.setProcedureStatus(ProcedureStatus.IN_PROGRESS);
            nextPage = "AddInProgressMedicalProcedure?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a procedure type.", null));
            return null;
        }

        this.procedureType = null;

        System.out.println("______________________________________new procedure created with values " + procedure);
        return nextPage;
    }


    public ProcedureDailyLog getProcedureLog() {
		return procedureLog;
	}

	public void setProcedureLog(ProcedureDailyLog procedureLog) {
		this.procedureLog = procedureLog;
	}

	public String createNewPrescription() throws ClassNotFoundException, SQLException
    {
    	prescription =new Prescription();
    	prescription.setPrescriptionId(providerEjb.generateNewPrescriptionId());
    	return "AddPrescription?faces-redirect=true";
    }
    public String createNewPrescribedMedicine() throws ClassNotFoundException, SQLException
    {
    	prescribedMedicines =new PrescribedMedicines();
    	prescribedMedicines.setPrescribedId(providerEjb.generateNewPrescribedMedicineId());
    	return "AddPrescribedMedicine?faces-redirect=true";
    }
    public String createNewProcedureTest() throws ClassNotFoundException, SQLException
    {
    	procedureTest =new ProcedureTest();
    	procedureTest.setTestId(providerEjb.generateNewProcedureTestId());
    	return "AddTest?faces-redirect=true";
    }
    public String createNewProcedureLog()
    {
     procedureLog=new ProcedureDailyLog();
     procedureLog.setLogId(providerEjb.generateNewProcedureLogId());
     return "AddProcedureLog?faces-redirect=true";
    	
    }
    public List<MedicalProcedure> getScheduledProceduresController()
    {
    	System.out.println("in controller");
    	
    	List<MedicalProcedure> updated= providerEjb.getScheduledProcedures();
    	System.out.println(updated);
    	return updated;
    }
	public List<MedicalProcedure> getInProgressProcedures()
	{
		return providerEjb.getInProgressProcedures();
	}

    public MedicalProcedure getProcedure() {
        return procedure;
    }


	public void setProcedure(MedicalProcedure procedure) {
		this.procedure = procedure;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public PrescribedMedicines getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(PrescribedMedicines prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

	public ProcedureTest getProcedureTest() {
		return procedureTest;
	}

	public void setProcedureTest(ProcedureTest procedureTest) {
		this.procedureTest = procedureTest;
	}

    public String procedureSubmit() {
        return "ProviderDashboard?faces-redirect=true";
    }

    public String prescriptionDetailsSubmit() {
        return "ProcedureDashboard?faces-redirect=true";
    }
    public String startProcedure(MedicalProcedure procedure) {
        System.out.println("in start procedure controller");

        // 1. Reload full procedure from DB using ID
        MedicalProcedure fullProc = providerEjb.getProcedureById(procedure.getProcedureId());
        System.out.println("obtained procedure is " + fullProc);

        // 2. Set status and fromDate
        fullProc.setProcedureStatus(ProcedureStatus.IN_PROGRESS);
        fullProc.setFromDate(new java.sql.Date(System.currentTimeMillis()));

        // 3. Update in DB
        providerEjb.updateProcedureStatus(fullProc);

        // 4. Set for view use
        this.selectedProcedure = fullProc;
        System.out.println("procedure set: " + selectedProcedure);

        // 5. Store required values in session
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("procedureId", fullProc.getProcedureId());
        sessionMap.put("providerId", fullProc.getProvider().getProviderId());
        sessionMap.put("doctorId", fullProc.getDoctor().getDoctorId());
        sessionMap.put("recipientHid", fullProc.getRecipient().gethId());
        sessionMap.put("fromDate", fullProc.getFromDate());
        sessionMap.put("procedureType", fullProc.getType()); // ⬅️ newly added
        sessionMap.put("procedureStatus", fullProc.getProcedureStatus()); // ⬅️ newly added

        // 6. Redirect to prescription entry page
        return "ViewProcedureDetails?faces-redirect=true";  // update path if needed
    }
    public String completeProcedure(MedicalProcedure procedure) {
        System.out.println("in completeProcedure controller");

        // 1. Reload full procedure from DB using ID
        MedicalProcedure fullProc = providerEjb.getProcedureById(procedure.getProcedureId());
        if (fullProc == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Procedure not found.", null));
            return null;
        }
        System.out.println("fetched procedure: " + fullProc);

        // 2. Set status and toDate
        fullProc.setProcedureStatus(ProcedureStatus.COMPLETED);
        fullProc.setToDate(new java.sql.Date(System.currentTimeMillis())); // set completion date

        // 3. Update in DB
        String result = providerEjb.updateProcedureStatus(fullProc);
        System.out.println("update result: " + result);

        // 4. Clear session if needed
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("procedureId");
        sessionMap.remove("fromDate");
        sessionMap.remove("procedureStatus");

        // 5. Optional: set selectedProcedure
        this.selectedProcedure = fullProc;

        // 6. Redirect to confirmation or listing page
        return "ProviderDashboard?faces-redirect=true";
    }

	public MedicalProcedure getSelectedProcedure() {
		return selectedProcedure;
	}

	public void setSelectedProcedure(MedicalProcedure selectedProcedure) {
		this.selectedProcedure = selectedProcedure;
	}
	public String goToAddProcedureDetails(MedicalProcedure p) {
	    System.out.println("in goToAddProcedureDetails controller");

	    // 1. Reload full procedure from DB using ID
	    MedicalProcedure fullProc = providerEjb.getProcedureById(p.getProcedureId());
	    System.out.println("obtained procedure is " + fullProc);

	    // 2. Store required values in session
	    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	    sessionMap.put("procedureId", fullProc.getProcedureId());
	    sessionMap.put("providerId", fullProc.getProvider().getProviderId());
	    sessionMap.put("doctorId", fullProc.getDoctor().getDoctorId());
	    sessionMap.put("recipientHid", fullProc.getRecipient().gethId());
	    sessionMap.put("fromDate", fullProc.getFromDate());
	    sessionMap.put("procedureType", fullProc.getType());
	    sessionMap.put("procedureStatus", fullProc.getProcedureStatus());

	    // 4. Redirect to the appropriate dashboard
	    return "LongTermProcedureDashboard?faces-redirect=true";  // Change path if needed
	}


   
}