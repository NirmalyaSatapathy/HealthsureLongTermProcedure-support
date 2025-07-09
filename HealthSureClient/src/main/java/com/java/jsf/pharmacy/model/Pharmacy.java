package com.java.jsf.pharmacy.model;

import java.util.Date;
import java.util.Set;

public class Pharmacy {
    private String pharmacyId;
    private String pharmacyName;
    private String contactNo;
    private String email;
    private String password;
    private Date createdAt;
    private String state;
    private String city;
    private String licenseNo;
    private String status;
    
    // One-to-Many relationships
    private Set<Medicine> medicines;
    private Set<Equipment> equipments;
    private Set<Pharmacist> pharmacists;
    private Set<DispensedMedicine> dispensedMedicines;
    private Set<DispensedEquipment> dispensedEquipments;
    private Set<PharmacyOtp> pharmacyOtps;
	public String getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
	public Set<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}
	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}
	public void setPharmacists(Set<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}
	public Set<DispensedMedicine> getDispensedMedicines() {
		return dispensedMedicines;
	}
	public void setDispensedMedicines(Set<DispensedMedicine> dispensedMedicines) {
		this.dispensedMedicines = dispensedMedicines;
	}
	public Set<DispensedEquipment> getDispensedEquipments() {
		return dispensedEquipments;
	}
	public void setDispensedEquipments(Set<DispensedEquipment> dispensedEquipments) {
		this.dispensedEquipments = dispensedEquipments;
	}
	public Set<PharmacyOtp> getPharmacyOtps() {
		return pharmacyOtps;
	}
	public void setPharmacyOtps(Set<PharmacyOtp> pharmacyOtps) {
		this.pharmacyOtps = pharmacyOtps;
	}
    
    // Constructors, getters and setters
}