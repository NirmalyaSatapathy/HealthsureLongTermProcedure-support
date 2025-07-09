package com.java.ejb.insurance.model;
 
import java.io.Serializable;
import java.util.Date;

import com.java.ejb.recipient.model.Recipient;
 
public class Subscribe implements Serializable{
    private String subscribeId;
 
    // ✅ Link to Recipient (foreign key h_id)
    private Recipient recipient;
 
    // ✅ Link to Insurance Coverage Option (foreign key coverage_id)
    private InsuranceCoverageOption coverageOption;
 
    private Date subscribeDate;
    private Date expiryDate;
    private String status;
    private double totalPremium;
    private double amountPaid = 0.00;
 
    // Getters and Setters
 
    public String getSubscribeId() {
        return subscribeId;
    }
 
    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }
 
    public Recipient getRecipient() {
        return recipient;
    }
 
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
 
    public InsuranceCoverageOption getCoverageOption() {
        return coverageOption;
    }
 
    public void setCoverageOption(InsuranceCoverageOption coverageOption) {
        this.coverageOption = coverageOption;
    }
 
    public Date getSubscribeDate() {
        return subscribeDate;
    }
 
    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }
 
    public Date getExpiryDate() {
        return expiryDate;
    }
 
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public double getTotalPremium() {
        return totalPremium;
    }
 
    public void setTotalPremium(double totalPremium) {
        this.totalPremium = totalPremium;
    }
 
    public double getAmountPaid() {
        return amountPaid;
    }
 
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
 
	public Subscribe() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Subscribe(String subscribeId, Recipient recipient, InsuranceCoverageOption coverageOption,
			Date subscribeDate, Date expiryDate, String status, double totalPremium, double amountPaid) {
		super();
		this.subscribeId = subscribeId;
		this.recipient = recipient;
		this.coverageOption = coverageOption;
		this.subscribeDate = subscribeDate;
		this.expiryDate = expiryDate;
		this.status = status;
		this.totalPremium = totalPremium;
		this.amountPaid = amountPaid;
	}
 
	@Override
	public String toString() {
		return "Subscribe [subscribeId=" + subscribeId + ", recipient=" + recipient + ", coverageOption="
				+ coverageOption + ", subscribeDate=" + subscribeDate + ", expiryDate=" + expiryDate + ", status="
				+ status + ", totalPremium=" + totalPremium + ", amountPaid=" + amountPaid + "]";
	}
 
   
}