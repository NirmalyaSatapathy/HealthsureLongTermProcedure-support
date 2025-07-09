package com.java.jsf.pharmacy.model;


import java.util.Set;

public class Pharmacist {
    private String pharmacistId;
    private Pharmacy pharmacy;
    private String pharmacistName;
    private String phoneNo;
    private String email;
    
    // One-to-Many relationships
    private Set<DispensedMedicine> dispensedMedicines;
    private Set<DispensedEquipment> dispensedEquipments;

    // Constructors
    public Pharmacist() {}
    
    public Pharmacist(String pharmacistId, Pharmacy pharmacy, String pharmacistName, 
                     String phoneNo, String email) {
        this.pharmacistId = pharmacistId;
        this.pharmacy = pharmacy;
        this.pharmacistName = pharmacistName;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    // Getters and Setters
    public String getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(String pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Pharmacist{" +
               "pharmacistId='" + pharmacistId + '\'' +
               ", pharmacy=" + pharmacy +
               ", pharmacistName='" + pharmacistName + '\'' +
               ", phoneNo='" + phoneNo + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}