package com.java.ejb.provider.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.java.ejb.admin.model.PaymentHistory;


public class Provider implements Serializable{

    private String providerId;
    private String name;
    private String type;
    private String contactNumber;
    private String address;
    private String email;
    public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Set<PaymentHistory> paymentHistory;
    public Set<PaymentHistory> getPaymentHistory() {
		return paymentHistory;
	}
	public void setPaymentHistory(Set<PaymentHistory> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
    @Override
	public String toString() {
		return "Provider [providerId=" + providerId + ", name=" + name + ", type=" + type + ", contactNumber="
				+ contactNumber + ", address=" + address + ", email=" + email + ", paymentHistory=" + paymentHistory
				+ ", doctors=" + doctors + ", procedures=" + procedures + ", appointments=" + appointments
				+ ", prescriptions=" + prescriptions + ", claims=" + claims + "]";
	}
	// Relationships
    private List<Doctor> doctors;
    public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	private List<MedicalProcedure> procedures;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions; // optional, if providers issue them
    private List<Claims> claims; // optional, if providers raise claims

    // Getters and Setters

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MedicalProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<MedicalProcedure> procedures) {
        this.procedures = procedures;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Claims> getClaims() {
        return claims;
    }

    public void setClaims(List<Claims> claims) {
        this.claims = claims;
    }
}
