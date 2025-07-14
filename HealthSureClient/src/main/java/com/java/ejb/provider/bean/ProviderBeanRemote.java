package com.java.ejb.provider.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureDailyLog;
import com.java.ejb.provider.model.ProcedureTest;

@Remote
public interface ProviderBeanRemote {
public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException;
public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException;
public String addTest(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException;
public String addPrescribedMedicines(PrescribedMedicines prescribedMedicines) throws ClassNotFoundException, SQLException;
public String generateNewProcedureId() throws ClassNotFoundException, SQLException;
public String generateNewPrescriptionId() throws ClassNotFoundException, SQLException;
public String generateNewPrescribedMedicineId() throws ClassNotFoundException, SQLException;
public String generateNewProcedureTestId() throws ClassNotFoundException, SQLException;
public String generateNewProcedureLogId();
public List<MedicalProcedure> getScheduledProceduresByDoctor(String doctorId, String procedureId);
public List<MedicalProcedure> getInProgressProceduresByDoctor(String doctorId, String procedureId);
public String updateProcedureStatus(MedicalProcedure procedure);
public MedicalProcedure getProcedureById(String id);
public String addProcedureDailyLog(ProcedureDailyLog log);
}
