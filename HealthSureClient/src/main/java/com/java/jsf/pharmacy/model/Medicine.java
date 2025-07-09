package com.java.jsf.pharmacy.model;

import java.util.Date;
import java.util.Set;

public class Medicine {
    private String medicineId;
    private Pharmacy pharmacy;
    private String medicineName;
    private String description;
    private Integer quantityInStock;
    private Date expiryDate;
    private Double unitPrice;
    private String purpose;
    private String batchNo;
    
    // One-to-Many relationships
    private Set<DispensedMedicine> dispensedMedicines;

    // Constructors
    public Medicine() {
    }

    public Medicine(String medicineId, Pharmacy pharmacy, String medicineName, 
                   String description, Integer quantityInStock, Date expiryDate,
                   Double unitPrice, String purpose, String batchNo) {
        this.medicineId = medicineId;
        this.pharmacy = pharmacy;
        this.medicineName = medicineName;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.unitPrice = unitPrice;
        this.purpose = purpose;
        this.batchNo = batchNo;
    }

    // Getters and Setters
    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Set<DispensedMedicine> getDispensedMedicines() {
        return dispensedMedicines;
    }

    public void setDispensedMedicines(Set<DispensedMedicine> dispensedMedicines) {
        this.dispensedMedicines = dispensedMedicines;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", pharmacy=" + pharmacy +
                ", medicineName='" + medicineName + '\'' +
                ", description='" + description + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", expiryDate=" + expiryDate +
                ", unitPrice=" + unitPrice +
                ", purpose='" + purpose + '\'' +
                ", batchNo='" + batchNo + '\'' +
                '}';
    }
}
