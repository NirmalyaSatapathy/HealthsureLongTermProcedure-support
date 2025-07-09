package com.java.ejb.provider.bean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.java.ejb.provider.model.Doctor;
import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureDailyLog;
import com.java.ejb.provider.model.ProcedureStatus;
import com.java.ejb.provider.model.ProcedureTest;
import com.java.ejb.provider.model.ProcedureType;
import com.java.ejb.provider.model.Provider;
import com.java.ejb.recipient.model.Recipient;

public class ProviderEjbImpl {
	static ProviderBeanRemote remote;
	static {
		try {
			remote = ProviderRemoteHelper.lookupRemoteStatelessProvider();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
	    // Store key fields in session map
	    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	    sessionMap.put("procedureId", medicalProcedure.getProcedureId());
	    sessionMap.put("providerId", medicalProcedure.getProvider().getProviderId());
	    sessionMap.put("doctorId", medicalProcedure.getDoctor().getDoctorId());
	    sessionMap.put("recipientHid", medicalProcedure.getRecipient().gethId());
	    sessionMap.put("procedureType", medicalProcedure.getType());
	    sessionMap.put("procedureStatus", medicalProcedure.getProcedureStatus());
	    // Conditionally store dates based on status
	    if (medicalProcedure.getType() == ProcedureType.LONG_TERM && medicalProcedure.getProcedureStatus()==ProcedureStatus.SCHEDULED) {
	        sessionMap.put("scheduledDate", medicalProcedure.getScheduledDate());
	        remote.addMedicalProcedure(medicalProcedure);
		    return "ProviderDashboard?faces-redirect=true";
	    } else if (medicalProcedure.getType() == ProcedureType.SINGLE_DAY) {
	        sessionMap.put("procedureDate", medicalProcedure.getProcedureDate());
	        remote.addMedicalProcedure(medicalProcedure);
		    return "ProcedureDashboard?faces-redirect=true";
	    }
	    else if(medicalProcedure.getType() == ProcedureType.LONG_TERM && medicalProcedure.getProcedureStatus() == ProcedureStatus.IN_PROGRESS)
	    {
	    	 sessionMap.put("fromDate", medicalProcedure.getFromDate());
		        remote.addMedicalProcedure(medicalProcedure);
			    return "LongTermProcedureDashboard?faces-redirect=true";
	    }
	    // Save to database via EJB
	   return null;
	}

	public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException {
	    // Save via remote EJB
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("prescriptionId",prescription.getPrescriptionId());
		System.out.println("calling remote prescription");
	    remote.addPrescription(prescription);
	    System.out.println("prescription added");
	    return "PrescriptionDashboard?faces-redirect=true";
	}


	public String addTest(ProcedureTest test) throws ClassNotFoundException, SQLException {
		remote.addTest(test);
		return "PrescriptionDashboard?faces-redirect=true";
	}

	public String addPrescribedMedicines(PrescribedMedicines prescribedMedicine)
			throws ClassNotFoundException, SQLException {
		remote.addPrescribedMedicines(prescribedMedicine);
		return "PrescriptionDashboard?faces-redirect=true";
	}
	public String generateNewProcedureId() throws ClassNotFoundException, SQLException
	{
		return remote.generateNewProcedureId();
	}
	public String generateNewPrescriptionId() throws ClassNotFoundException, SQLException
	{
		return remote.generateNewPrescriptionId();
	}
	public String generateNewPrescribedMedicineId() throws ClassNotFoundException, SQLException
	{
		return remote.generateNewPrescribedMedicineId();
	}
	public String generateNewProcedureTestId() throws ClassNotFoundException, SQLException
	{
		return remote.generateNewProcedureTestId();
	}
	public String generateNewProcedureLogId()
	{
		return remote.generateNewProcedureLogId();
	}
	public List<MedicalProcedure> getScheduledProcedures()
	{
		return remote.getScheduledProcedures();
	}
	public List<MedicalProcedure> getInProgressProcedures()
	{
		return remote.getInProgressProcedures();
	}
	public String updateProcedureStatus(MedicalProcedure procedure)
	{
		System.out.println("in ejb impl "+procedure);
		return remote.updateProcedureStatus(procedure);
	}
	public MedicalProcedure getProcedureById(String id)
	{
		return remote.getProcedureById(id);
	}

	public String addProcedureLog(ProcedureDailyLog procedureLog) {
		// TODO Auto-generated method stub
		remote.addProcedureDailyLog(procedureLog);
		return "LongTermProcedureDashboard?faces-redirect=true";
	}
}
