package com.java.ejb.provider.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.java.ejb.recipient.model.Recipient;

public class Prescription implements Serializable{

    private String prescriptionId;

    // Foreign key object mappings
    private MedicalProcedure procedure;    // mapped from procedure_id
    private Recipient recipient;           // mapped from h_id
    private Provider provider;             // mapped from provider_id
    private Doctor doctor;                 // mapped from doctor_id

    // Other fields
    private Date writtenOn;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private List<PrescribedMedicines> prescribedMedicines;
    private List<ProcedureTest> tests;
    public List<ProcedureTest> getTests() {
		return tests;
	}

	public void setTests(List<ProcedureTest> tests) {
		this.tests = tests;
	}

	// Getters and Setters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public MedicalProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(MedicalProcedure procedure) {
        this.procedure = procedure;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getWrittenOn() {
        return writtenOn;
    }

    public void setWrittenOn(Date writtenOn) {
        this.writtenOn = writtenOn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	 @Override
	    public String toString() {
	        return "Prescription [" +
	                "prescriptionId=" + prescriptionId +
	                ", procedureId=" + (procedure != null ? procedure.getProcedureId() : null) +
	                ", recipientId=" + (recipient != null ? recipient.gethId() : null) +
	                ", providerId=" + (provider != null ? provider.getProviderId() : null) +
	                ", doctorId=" + (doctor != null ? doctor.getDoctorId() : null) +
	                ", writtenOn=" + writtenOn +
	                ", createdAt=" + createdAt +
	                ", startDate=" + startDate +
	                ", endDate=" + endDate +
	                ", prescribedMedicineCount=" + (prescribedMedicines != null ? prescribedMedicines.size() : 0) +
	                ", testCount=" + (tests != null ? tests.size() : 0) +
	                "]";
	    }

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<PrescribedMedicines> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(List<PrescribedMedicines> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

	public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Prescription() {
        this.procedure = new MedicalProcedure();
        this.recipient = new Recipient();
        this.provider = new Provider();
        this.doctor = new Doctor();
    }
}
