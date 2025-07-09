package com.java.ejb.provider.dao;
import java.sql.SQLException;

import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureTest;

public interface ProviderDao {
	public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, java.sql.SQLException;
	public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException;
	public String addTest(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException;
	public String addPrescribedMedicines(PrescribedMedicines prescribedMedicines) throws ClassNotFoundException, SQLException;
	public String generateNewPrescriptionId() throws ClassNotFoundException, SQLException;
	public String generateNewPrescribedMedicineId() throws ClassNotFoundException, SQLException;
	public String generateNewProcedureTestId() throws ClassNotFoundException, SQLException;
	String generateNewProcedureId() throws SQLException, ClassNotFoundException;
}
