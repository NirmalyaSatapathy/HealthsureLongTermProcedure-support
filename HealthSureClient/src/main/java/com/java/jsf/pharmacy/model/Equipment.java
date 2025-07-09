package com.java.jsf.pharmacy.model;

import java.util.Date;
import java.util.Set;

public class Equipment {
    private String equipmentId;
    private Pharmacy pharmacy;
    private String equipmentName;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private String purpose;
    private Date purchaseDate;
    private String status;
    private Set<DispensedEquipment> dispensedEquipments;
	public Set<DispensedEquipment> getDispensedEquipments() {
		return dispensedEquipments;
	}
	public void setDispensedEquipments(Set<DispensedEquipment> dispensedEquipments) {
		this.dispensedEquipments = dispensedEquipments;
	}
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    // Constructors, getters and setters
}
