package com.java.jsf.provider.dao;

import java.util.List;

import com.java.jsf.provider.model.PatientInsuranceDetails;

public interface InsuranceDao {
public List<PatientInsuranceDetails> showInsuranceOfRecipient(String recipientId);
}
