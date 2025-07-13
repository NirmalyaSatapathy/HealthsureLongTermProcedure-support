package com.java.jsf.provider.controller;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import com.java.jsf.util.Converter;
import com.java.jsf.util.ProcedureIdGenerator;
import com.java.ejb.provider.model.Appointment;

public class ProcedureController {
    private ProviderEjbImpl providerEjb;
    private ProviderDaoImpl providerDao;
    Prescription prescription;
    MedicalProcedure procedure;
    PrescribedMedicines prescribedMedicine;
    ProcedureTest procedureTest;
    ProcedureDailyLog procedureLog;
    List<Prescription> prescriptions=new ArrayList<Prescription>();
    List<PrescribedMedicines> prescribedMedicines=new ArrayList<PrescribedMedicines>();
    List<ProcedureTest> procedureTests=new ArrayList<ProcedureTest>();
    List<ProcedureDailyLog> procedureLogs=new ArrayList<ProcedureDailyLog>();
    private String procedureType; // "single" or "scheduled"
    MedicalProcedure selectedProcedure;
    private String doctorId;
    private String procedureId;
    private List<MedicalProcedure> scheduledProcedures;

    // ✅ Getters & setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public List<MedicalProcedure> getScheduledProcedures() {
        return scheduledProcedures;
    }

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

        String hId = medicalProcedure.getRecipient().gethId();
        Recipient recipient = providerDao.searchRecipientByHealthId(hId);
        if (recipient == null || !recipient.gethId().equalsIgnoreCase(hId)) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String providerId = medicalProcedure.getProvider().getProviderId();
        Provider provider = providerDao.searchProviderById(providerId);
        if (provider == null || !provider.getProviderId().equalsIgnoreCase(providerId)) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String doctorId = medicalProcedure.getDoctor().getDoctorId();
        Doctor doctor = providerDao.searchDoctorById(doctorId);
        if (doctor == null || !doctor.getDoctorId().equalsIgnoreCase(doctorId)) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String appointmentId = medicalProcedure.getAppointment().getAppointmentId();
        Appointment appointment = providerDao.searchAppointmentById(appointmentId);
        if (appointment == null || !appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equalsIgnoreCase(providerId)) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getDoctor().getDoctorId().equalsIgnoreCase(doctorId)) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getRecipient().gethId().equalsIgnoreCase(hId)) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        // Validate Diagnosis
        if (medicalProcedure.getDiagnosis() == null || medicalProcedure.getDiagnosis().trim().length() < 2) {
            context.addMessage("diagnosis", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Diagnosis", "Diagnosis must contain at least 2 letters."));
            context.validationFailed();
            isValid = false;
        }

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

        if (!isValid) return null;

        medicalProcedure.setFromDate(null);
        medicalProcedure.setToDate(null);
        medicalProcedure.setScheduledDate(null);
        medicalProcedure.setType(ProcedureType.SINGLE_DAY);
        medicalProcedure.setProcedureStatus(ProcedureStatus.COMPLETED);
        return "ProcedureDashboard?faces-redirect=true";
    }
    public String addScheduledMedicalProcedureController(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        String hId = medicalProcedure.getRecipient().gethId();
        Recipient recipient = providerDao.searchRecipientByHealthId(hId);
        if (recipient == null || !recipient.gethId().equalsIgnoreCase(hId)) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String providerId = medicalProcedure.getProvider().getProviderId();
        Provider provider = providerDao.searchProviderById(providerId);
        if (provider == null || !provider.getProviderId().equalsIgnoreCase(providerId)) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String doctorId = medicalProcedure.getDoctor().getDoctorId();
        Doctor doctor = providerDao.searchDoctorById(doctorId);
        if (doctor == null || !doctor.getDoctorId().equalsIgnoreCase(doctorId)) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String appointmentId = medicalProcedure.getAppointment().getAppointmentId();
        Appointment appointment = providerDao.searchAppointmentById(appointmentId);
        if (appointment == null || !appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equalsIgnoreCase(providerId)) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getDoctor().getDoctorId().equalsIgnoreCase(doctorId)) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getRecipient().gethId().equalsIgnoreCase(hId)) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        // Validate Diagnosis
        if (medicalProcedure.getDiagnosis() == null || medicalProcedure.getDiagnosis().trim().length() < 2) {
            context.addMessage("diagnosis", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Diagnosis", "Diagnosis must contain at least 2 letters."));
            context.validationFailed();
            isValid = false;
        }

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

        if (!isValid) return null;

        medicalProcedure.setProcedureDate(null);
        medicalProcedure.setFromDate(null);
        medicalProcedure.setToDate(null);
        medicalProcedure.setType(ProcedureType.LONG_TERM);
        medicalProcedure.setProcedureStatus(ProcedureStatus.SCHEDULED);

        String res = providerEjb.addMedicalProcedure(medicalProcedure);
        this.procedure = null;
        return res;
    }

    public String addInProgressMedicalProcedureController(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
        providerDao = new ProviderDaoImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        String hId = medicalProcedure.getRecipient().gethId();
        Recipient recipient = providerDao.searchRecipientByHealthId(hId);
        if (recipient == null || !recipient.gethId().equalsIgnoreCase(hId)) {
            context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Patient", "Recipient with given Health ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String providerId = medicalProcedure.getProvider().getProviderId();
        Provider provider = providerDao.searchProviderById(providerId);
        if (provider == null || !provider.getProviderId().equalsIgnoreCase(providerId)) {
            context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Provider", "Provider with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String doctorId = medicalProcedure.getDoctor().getDoctorId();
        Doctor doctor = providerDao.searchDoctorById(doctorId);
        if (doctor == null || !doctor.getDoctorId().equalsIgnoreCase(doctorId)) {
            context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Doctor", "Doctor with given ID not found."));
            context.validationFailed();
            isValid = false;
        }

        String appointmentId = medicalProcedure.getAppointment().getAppointmentId();
        Appointment appointment = providerDao.searchAppointmentById(appointmentId);
        if (appointment == null || !appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
            context.addMessage("appointmentId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Appointment", "Appointment with given ID not found."));
            context.validationFailed();
            isValid = false;
        } else {
            if (!appointment.getProvider().getProviderId().equalsIgnoreCase(providerId)) {
                context.addMessage("providerId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not belong to the selected provider."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getDoctor().getDoctorId().equalsIgnoreCase(doctorId)) {
                context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment does not involve the selected doctor."));
                context.validationFailed();
                isValid = false;
            }
            if (!appointment.getRecipient().gethId().equalsIgnoreCase(hId)) {
                context.addMessage("recipientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mismatch", "This appointment is not for the selected patient."));
                context.validationFailed();
                isValid = false;
            }
        }

        // Validate Diagnosis
        if (medicalProcedure.getDiagnosis() == null || medicalProcedure.getDiagnosis().trim().length() < 2) {
            context.addMessage("diagnosis", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Diagnosis", "Diagnosis must contain at least 2 letters."));
            context.validationFailed();
            isValid = false;
        }

        if (!isValid) return null;

        medicalProcedure.setScheduledDate(null);
        medicalProcedure.setProcedureDate(null);
        medicalProcedure.setToDate(null);
        medicalProcedure.setType(ProcedureType.LONG_TERM);
        medicalProcedure.setProcedureStatus(ProcedureStatus.IN_PROGRESS);
        return "LongTermProcedureDashboard?faces-redirect=true";
    }

    public String addTestController(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException {
    	  procedureTests.removeIf(p -> p.getTestId().equals(procedureTest.getTestId()));
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();

      procedureTest.setPrescription(prescription);
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

        Date prescriptionStart =prescription.getStartDate();
        Date prescriptionEnd =prescription.getEndDate();

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
      
        procedureTests.add(procedureTest);
        return "PrescriptionDashboard?faces-redirect=true";
        }



    public String addPresribedMedicinesController(PrescribedMedicines prescribedMedicine)
            throws ClassNotFoundException, SQLException {
    	   prescribedMedicines.removeIf(p -> p.getPrescribedId().equals(prescribedMedicine.getPrescribedId()));
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();
        prescribedMedicine.setPrescription(prescription);

     // 1. Medicine Name Validation
        String medicineName = prescribedMedicine.getMedicineName();
        if (medicineName == null || !medicineName.matches("^[a-zA-Z0-9()\\-+/'. ]{2,50}$")) {
            context.addMessage("medicineName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Medicine name must be 2–50 characters and can include letters, digits, -, /, +, (), '.', and spaces.", null));
            return null;
        }
     
        // Normalize medicine name
        medicineName = medicineName.trim().replaceAll("\\s+", " ");
        prescribedMedicine.setMedicineName(medicineName);
        // 3. Check for duplicate medicine in same prescription
        for (PrescribedMedicines existingMed : prescribedMedicines) {
            if (existingMed.getPrescription() != null &&
                existingMed.getPrescription().getPrescriptionId().equals(prescription.getPrescriptionId()) &&
                existingMed.getMedicineName() != null &&
                existingMed.getMedicineName().equalsIgnoreCase(medicineName)) {
                
                context.addMessage("medicineName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "This medicine is already prescribed in this prescription.", null));
                return null;
            }
        }

        // 3. Dosage Validation
        String dosage = prescribedMedicine.getDosage();
        if (dosage == null || dosage.trim().isEmpty()) {
            context.addMessage("dosage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dosage is required.", null));
            return null;
        }

        MedicineType type = prescribedMedicine.getType();
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
        Date prescriptionStart = prescription.getStartDate();
        Date prescriptionEnd = prescription.getEndDate();
        Date medStart = prescribedMedicine.getStartDate();
        Date medEnd = prescribedMedicine.getEndDate();

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
            int durationDays = Integer.parseInt(prescribedMedicine.getDuration().trim());

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
        prescribedMedicines.add(prescribedMedicine);
        return"PrescriptionDashboard?faces-resirect=true";
    }

    public String addPrescriptionController(Prescription prescription) throws ClassNotFoundException, SQLException {
    	 prescriptions.removeIf(p -> p.getPrescriptionId().equals(prescription.getPrescriptionId()));
    	 FacesContext context = FacesContext.getCurrentInstance();
        prescription.setProcedure(procedure);
        prescription.setProvider(procedure.getProvider());
        prescription.setDoctor(procedure.getDoctor());
        prescription.setRecipient(procedure.getRecipient());
        // Validate writtenOn field
        if (prescription.getWrittenOn() == null) {
            context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Please enter the Written On date.", null));
            return null;
        }

        // Normalize writtenOn date
        Date writtenOn =Converter.truncateTime(prescription.getWrittenOn());

        // ✅ Fetch procedure details
        ProcedureType procedureType = procedure.getType();
        ProcedureStatus procedureStatus = procedure.getProcedureStatus();

        Date procedureDate = procedure.getProcedureDate(); // for SINGLE_DAY
        Date fromDate = procedure.getFromDate();         // for LONG_TERM + IN_PROGRESS

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

            Date truncatedProcedureDate = Converter.truncateTime(procedureDate);

            if (!writtenOn.equals(truncatedProcedureDate)) {
                context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Written On date (" + writtenOn +
                                ") must exactly match the Procedure Date (" + truncatedProcedureDate + ").+ for single day procedures", null));
                return null;
            }
        }

        System.out.println(fromDate);
        System.out.println(prescription.getWrittenOn());
        System.out.println("reached for validation of longterm in progress");

        // ✅ LONG_TERM + IN_PROGRESS: writtenOn must be after fromDate
        if (procedureType == ProcedureType.LONG_TERM &&
                procedureStatus == ProcedureStatus.IN_PROGRESS) {

            if (fromDate == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Missing From Date for LONG_TERM procedure ID: " + procedureId, null));
                return null;
            }

            Date truncatedFromDate = Converter.truncateTime(fromDate);

            if (writtenOn.before(truncatedFromDate)) {
                context.addMessage("writtenOn", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Written On date (" + writtenOn +
                                ") must be after the Procedure From Date (" + truncatedFromDate + ") for long-term in-progress procedures.", null));
                return null;
            }
        }

        // Validate prescription start and end dates
        Date startDate = Converter.truncateTime(prescription.getStartDate());
        Date endDate = Converter.truncateTime(prescription.getEndDate());

        if (startDate.before(writtenOn)) {
            context.addMessage("startDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Prescription start date (" + startDate +
                            ") cannot be before the prescription written date (" + writtenOn + ").", null));
            return null;
        }

        if (endDate.before(startDate)) {
            context.addMessage("endDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Prescription end date (" + endDate +
                            ") cannot be before the prescription start date (" + startDate + ").", null));
            return null;
        }

        // Save the actual validated values back (optional but good for consistency)
        prescription.setWrittenOn(writtenOn);
        prescription.setStartDate(startDate);
        prescription.setEndDate(endDate);
        System.out.println(prescriptions);
        System.out.println(prescription);
        prescriptions.add(prescription);
	    return "PrescriptionDashboard?faces-redirect=true";
        
    }

    public String addProcedureLogController(ProcedureDailyLog procedureLog) throws ClassNotFoundException, SQLException {
    	procedureLogs.removeIf(p -> p.getLogId().equals(procedureLog.getLogId()));
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        providerDao = new ProviderDaoImpl();

        procedureLog.setMedicalProcedure(procedure);

        // 1. Validate Log Date
        Date logDate = procedureLog.getLogDate();
        if (logDate == null) {
            context.addMessage("logDate", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Log date is required.", null));
            return null;
        }

        // Get procedure fromDate
        Date fromDate = procedure.getFromDate();
        if (fromDate == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Unable to fetch procedure's start date for validation.", null));
            return null;
        }

        // Truncate time parts of both dates before comparing
        Calendar logCal = Calendar.getInstance();
        logCal.setTime(logDate);
        logCal.set(Calendar.HOUR_OF_DAY, 0);
        logCal.set(Calendar.MINUTE, 0);
        logCal.set(Calendar.SECOND, 0);
        logCal.set(Calendar.MILLISECOND, 0);

        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        if (logCal.getTime().before(fromCal.getTime())) {
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
            if (!vitals.matches("[a-zA-Z0-9:/.,\\s]+")) {
                context.addMessage("vitals", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Vitals can only contain letters, numbers, spaces, colon (:), slash (/), comma (,), and dot (.)", null));
                return null;
            }

            // Rule 3: Should not be numeric only
            if (vitals.matches("\\d+")) {
                context.addMessage("vitals", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Vitals cannot be only numeric. Please include proper labels like 'BP: 120/80'.", null));
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
        
        procedureLogs.add(procedureLog);
        return"LongTermProcedureDashboard?faces-redirect=true";
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
	public String createNewPrescription() throws ClassNotFoundException, SQLException {
	    prescription = new Prescription();

	    if (prescriptions != null && !prescriptions.isEmpty()) {
	        prescription.setPrescriptionId(ProcedureIdGenerator.getNextPrescriptionId(prescriptions));
	    } else {
	        prescription.setPrescriptionId(providerEjb.generateNewPrescriptionId());
	    }

	    return "AddPrescription?faces-redirect=true";
	}
	public String createNewPrescribedMedicine() throws ClassNotFoundException, SQLException {
	    prescribedMedicine = new PrescribedMedicines();

	    if (prescribedMedicines != null && !prescribedMedicines.isEmpty()) {
	        prescribedMedicine.setPrescribedId(ProcedureIdGenerator.getNextPrescribedMedicineId(prescribedMedicines));
	    } else {
	        prescribedMedicine.setPrescribedId(providerEjb.generateNewPrescribedMedicineId());
	    }

	    return "AddPrescribedMedicine?faces-redirect=true";
	}

	public String createNewProcedureTest() throws ClassNotFoundException, SQLException {
	    procedureTest = new ProcedureTest();

	    if (procedureTests != null && !procedureTests.isEmpty()) {
	        procedureTest.setTestId(ProcedureIdGenerator.getNextProcedureTestId(procedureTests));
	    } else {
	        procedureTest.setTestId(providerEjb.generateNewProcedureTestId());
	    }

	    return "AddTest?faces-redirect=true";
	}
	public String createNewProcedureLog() {
	    procedureLog = new ProcedureDailyLog();

	    if (procedureLogs != null && !procedureLogs.isEmpty()) {
	        procedureLog.setLogId(ProcedureIdGenerator.getNextProcedureLogId(procedureLogs));
	    } else {
	        try {
	            procedureLog.setLogId(providerEjb.generateNewProcedureLogId());
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle DB fallback error appropriately
	        }
	    }

	    return "AddProcedureLog?faces-redirect=true";
	}

    public String fetchScheduledProceduresController() {
        providerDao = new ProviderDaoImpl();
        allScheduledProcedures = null;
        scheduledProcedures = null;

        if (doctorId == null || doctorId.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("doctorId",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor ID is required", null));
            return null;
        }

        Doctor doctor = providerDao.searchDoctorById(doctorId);
        if (doctor == null) {
            FacesContext.getCurrentInstance().addMessage("doctorId",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor with ID " + doctorId + " does not exist.", null));
            return null;
        }

        List<MedicalProcedure> procedures;
        if (procedureId != null && !procedureId.trim().isEmpty()) {
            procedures = providerEjb.getScheduledProceduresByDoctor(doctorId, procedureId);
            if (procedures.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("procedureId",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "No scheduled procedure found with ID " + procedureId + " for Doctor ID " + doctorId, null));
                return null;
            }
        } else {
            procedures = providerEjb.getScheduledProceduresByDoctor(doctorId, null);
            if (procedures.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("doctorId",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No scheduled procedures found for Doctor ID " + doctorId, null));
            }
        }

        allScheduledProcedures = procedures;
        currentPage = 1;
        paginate();
        return null;
    }
    public void paginate() {
        if (allScheduledProcedures == null) return;

        int total = allScheduledProcedures.size();
        totalPages = (int) Math.ceil((double) total / pageSize);
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);

        scheduledProcedures = allScheduledProcedures.subList(fromIndex, toIndex);
    }
    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            paginate();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            paginate();
        }
    }
    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public boolean hasNextPage() {
        return currentPage < totalPages;
    }
    public void goToFirstPage() {
        currentPage = 1;
        paginate();  // refresh the current page content
    }

    public void goToLastPage() {
        currentPage = totalPages > 0 ? totalPages : 1;
        paginate();  // refresh the current page content
    }



// Optional: or stay on same page if no navigation
    private List<MedicalProcedure> allScheduledProcedures; // complete data for sorting

    public List<MedicalProcedure> getAllScheduledProcedures() {
		return allScheduledProcedures;
	}

	public void setAllScheduledProcedures(List<MedicalProcedure> allScheduledProcedures) {
		this.allScheduledProcedures = allScheduledProcedures;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setScheduledProcedures(List<MedicalProcedure> scheduledProcedures) {
		this.scheduledProcedures = scheduledProcedures;
	}
	private int currentPage = 1;
    private int pageSize = 3;
    private int totalPages;
        private String sortField;
        private boolean sortAscending = true;
        // Getters and Setters for sorting
        public String getSortField() {
            return sortField;
        }

        public void setSortField(String sortField) {
            this.sortField = sortField;
        }

        public boolean isSortAscending() {
            return sortAscending;
        }

        public void setSortAscending(boolean sortAscending) {
            this.sortAscending = sortAscending;
        }
        // Sorting logic
        public void sortBy(String field) {
            if (field.equals(sortField)) {
                sortAscending = !sortAscending; // toggle direction
            } else {
                sortField = field;
                sortAscending = true; // default ascending on new field
            }

            Comparator<MedicalProcedure> comparator = getComparatorForField(field);
            if (comparator != null && allScheduledProcedures != null) {
                if (!sortAscending) {
                    comparator = comparator.reversed();
                }
                allScheduledProcedures.sort(comparator);
                currentPage = 1;
                paginate();
            }
        }


        // Comparator mapping for each field
        private Comparator<MedicalProcedure> getComparatorForField(String field) {
            switch (field) {
                case "procedureId":
                    return Comparator.comparing(MedicalProcedure::getProcedureId);
                case "scheduledDate":
                    return Comparator.comparing(MedicalProcedure::getScheduledDate);
                case "recipientFirstName":
                    return Comparator.comparing(p -> p.getRecipient().getFirstName(), Comparator.nullsLast(String::compareTo));
                case "recipientLastName":
                    return Comparator.comparing(p -> p.getRecipient().getLastName(), Comparator.nullsLast(String::compareTo));
                case "doctorName":
                    return Comparator.comparing(p -> p.getDoctor().getDoctorName(), Comparator.nullsLast(String::compareTo));
                case "providerName":
                    return Comparator.comparing(p -> p.getProvider().getName(), Comparator.nullsLast(String::compareTo));
                case "appointmentId":
                    return Comparator.comparing(p -> p.getAppointment().getAppointmentId());
                default:
                    return null;
            }
        }

        public String resetPage() {
            // Reset form input fields
            this.doctorId = null;
            this.procedureId = null;

            // Clear data lists
            this.allScheduledProcedures = null;
            this.scheduledProcedures = null;

            // Reset sorting
            this.sortField = null;
            this.sortAscending = true;

            // Reset pagination
            this.currentPage = 1;
            this.totalPages = 1;
            this.pageSize = 10; // or your default page size

            // Optional: reset internal tracking states if any
            this.selectedProcedure = null;
           
            // Clear JSF view tree to force reload
            FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();

            // Redirect to the same page to reset everything
            return "scheduledProcedures?faces-redirect=true";
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

	public PrescribedMedicines getPrescribedMedicine() {
		return prescribedMedicine;
	}

	public void setPrescribedMedicine(PrescribedMedicines prescribedMedicine) {
		this.prescribedMedicine = prescribedMedicine;
	}

	public ProcedureTest getProcedureTest() {
		return procedureTest;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<PrescribedMedicines> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(List<PrescribedMedicines> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

	public List<ProcedureTest> getProcedureTests() {
		return procedureTests;
	}

	public void setProcedureTests(List<ProcedureTest> procedureTests) {
		this.procedureTests = procedureTests;
	}

	public List<ProcedureDailyLog> getProcedureLogs() {
		return procedureLogs;
	}

	public void setProcedureLogs(List<ProcedureDailyLog> procedureLogs) {
		this.procedureLogs = procedureLogs;
	}

	public void setProcedureTest(ProcedureTest procedureTest) {
		this.procedureTest = procedureTest;
	}

	public String procedureSubmit() {
	    try {
	        // Step 1: Save the procedure
	    	if(providerEjb.generateNewProcedureId().equals(procedure.getProcedureId()))
	    	{
	      providerEjb.addMedicalProcedure(procedure);
	        // Step 2: Set the saved procedure reference in each related object and save them
	    	}
	        // Save prescriptions
	        for (Prescription p : prescriptions) {
	          // assuming there's a `procedure` field in Prescription
	            providerEjb.addPrescription(p);
	        }

	        // Save prescribed medicines
	        for (PrescribedMedicines pm : prescribedMedicines) {
	             // or use p.setPrescription() if it's linked through prescription
	            providerEjb.addPrescribedMedicines(pm);
	        }

	        // Save tests
	        for (ProcedureTest test : procedureTests) {
	            providerEjb.addTest(test);
	        }
	        if(procedure.getType()==ProcedureType.LONG_TERM)
	        	
	        {
	        // Save logs
	        for (ProcedureDailyLog log : procedureLogs) {
	         
	            providerEjb.addProcedureLog(log);
	        }
	        }
	        // Step 3: Clear the session-level data (optional if session-scope controller)
	        procedure=null;
	        prescription=null;
	        prescriptions.clear();
	        
	       prescribedMedicine=null;
	       prescribedMedicines.clear();
	       procedureTest=null;
	       procedureTests.clear();
	       procedureLog=null;
	       procedureLogs.clear();
	    		   

	        // Step 4: Redirect to dashboard
	        return "ProviderDashboard?faces-redirect=true";

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "ErrorPage?faces-redirect=true";
	    }
	}


    public String prescriptionDetailsSubmit() {
    	String result="";
    	if(procedure.getProcedureStatus()==ProcedureStatus.COMPLETED && procedure.getType()==ProcedureType.SINGLE_DAY)
    	{
        result="ProcedureDashboard?faces-redirect=true";
    	}
    	if(procedure.getProcedureStatus()==ProcedureStatus.IN_PROGRESS && procedure.getType()==ProcedureType.LONG_TERM)
    	{
    		result="LongTermProcedureDashboard?faces-redirect=true";
    	}
    	return result;
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

       this.procedure=fullProc;

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
        return "ShowOnGoingProcedures?faces-redirect=true";
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
this.procedure=fullProc;

	    // 4. Redirect to the appropriate dashboard
	    return "LongTermProcedureDashboard?faces-redirect=true";  // Change path if needed
	}


   
}