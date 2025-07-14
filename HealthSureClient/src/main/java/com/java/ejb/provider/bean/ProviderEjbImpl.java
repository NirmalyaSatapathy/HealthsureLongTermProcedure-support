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
	    if (medicalProcedure.getType() == ProcedureType.LONG_TERM && medicalProcedure.getProcedureStatus()==ProcedureStatus.SCHEDULED) {
		      remote.addMedicalProcedure(medicalProcedure);
		      return "ProcedureOptions";
	    } else if (medicalProcedure.getType() == ProcedureType.SINGLE_DAY) {
		    return remote.addMedicalProcedure(medicalProcedure);
	    }
	    else if(medicalProcedure.getType() == ProcedureType.LONG_TERM && medicalProcedure.getProcedureStatus() == ProcedureStatus.IN_PROGRESS)
	    {
			    return  remote.addMedicalProcedure(medicalProcedure);
	    }
	    // Save to database via EJB
	   return null;
	}

	public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException {
		System.out.println("calling remote prescription");
	    return remote.addPrescription(prescription);
	    
	}


	public String addTest(ProcedureTest test) throws ClassNotFoundException, SQLException {
	
		return 	remote.addTest(test);
	}

	public String addPrescribedMedicines(PrescribedMedicines prescribedMedicine)
			throws ClassNotFoundException, SQLException {
		
		return remote.addPrescribedMedicines(prescribedMedicine);
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
	public List<MedicalProcedure> getScheduledProceduresByDoctor(String doctorId, String procedureId)
	{
		return remote.getScheduledProceduresByDoctor(doctorId, procedureId);
	}
	public List<MedicalProcedure> getInProgressProceduresByDoctor(String doctorId, String procedureId)
	{
		return remote.getInProgressProceduresByDoctor(doctorId, procedureId);
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
		
		return remote.addProcedureDailyLog(procedureLog);
	}
}
