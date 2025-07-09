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
    @Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", procedure=" + procedure + ", recipient="
				+ recipient + ", provider=" + provider + ", doctor=" + doctor + ", writtenOn=" + writtenOn
				+ ", createdAt=" + createdAt + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", prescribedMedicines=" + prescribedMedicines + "]";
	}
	private Date createdAt;
    private Date startDate;
    private Date endDate;
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
