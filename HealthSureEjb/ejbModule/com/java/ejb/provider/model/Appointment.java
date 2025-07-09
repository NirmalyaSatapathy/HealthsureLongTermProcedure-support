package com.java.ejb.provider.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.java.ejb.recipient.model.Recipient;
public class Appointment implements Serializable {

    private String appointmentId;
    private Doctor doctor;
    private Recipient recipient;
    private DoctorAvailability availability;
    private Provider provider;

    private Date requestedAt;
    private Date bookedAt;
    private String status;
    private String notes;

    public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	// One-to-many: One appointment can have multiple procedures
    private Set<MedicalProcedure> procedures;

    // Getters and Setters

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public DoctorAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(DoctorAvailability availability) {
        this.availability = availability;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Date getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Date bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<MedicalProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<MedicalProcedure> procedures) {
        this.procedures = procedures;
    }
}
