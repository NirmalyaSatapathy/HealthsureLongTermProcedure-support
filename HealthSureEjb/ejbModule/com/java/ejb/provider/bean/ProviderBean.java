package com.java.ejb.provider.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.provider.dao.ProviderDao;
import com.java.ejb.provider.daoImpl.ProviderDaoImpl;
import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureDailyLog;
import com.java.ejb.provider.model.ProcedureTest;

/**
 * Session Bean implementation class ProviderBean
 */
@Stateless
@Remote(ProviderBeanRemote.class)
public class ProviderBean implements ProviderBeanRemote {

    /**
     * Default constructor. 
     */
	static ProviderDao providerDao;
	static
	{
		providerDao=new ProviderDaoImpl();
	}
    public ProviderBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.addMedicalProcedure(medicalProcedure);
	}

	@Override
	public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.addPrescription(prescription);
	}

	@Override
	public String addTest(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.addTest(procedureTest);
	}

	@Override
	public String addPrescribedMedicines(PrescribedMedicines prescribedMedicines) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.addPrescribedMedicines(prescribedMedicines);
	}

	@Override
	public String generateNewProcedureId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.generateNewProcedureId();
	}

	@Override
	public String generateNewPrescriptionId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.generateNewPrescriptionId();
	}

	@Override
	public String generateNewPrescribedMedicineId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.generateNewPrescribedMedicineId();
	}

	@Override
	public String generateNewProcedureTestId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.generateNewProcedureTestId();
	}

	@Override
	public List<MedicalProcedure> getScheduledProceduresByDoctor(String doctorId, String procedureId) {
		// TODO Auto-generated method stub
		return providerDao.getScheduledProceduresByDoctor(doctorId,procedureId);
	}

	

	@Override
	public String updateProcedureStatus(MedicalProcedure procedure) {
		// TODO Auto-generated method stub
		return providerDao.updateProcedureStatus(procedure);
	}

	@Override
	public MedicalProcedure getProcedureById(String id) {
		// TODO Auto-generated method stub
		return providerDao.getProcedureById(id);
	}

	@Override
	public String generateNewProcedureLogId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.generateNewProcedureLogId();
	}

	@Override
	public String addProcedureDailyLog(ProcedureDailyLog log) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return providerDao.addProcedureDailyLog(log);
	}

	@Override
	public List<MedicalProcedure> getInProgressProceduresByDoctor(String doctorId, String procedureId) {
		// TODO Auto-generated method stub
		return providerDao.getInProgressProceduresByDoctor(doctorId, procedureId);
	}

}
