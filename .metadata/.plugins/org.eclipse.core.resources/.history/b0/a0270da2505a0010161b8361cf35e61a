package com.java.ejb.provider.bean;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.provider.model.MedicalProcedure;
import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureTest;

@Remote
public interface ProviderBeanRemote {
public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException;
public String addPrescription(Prescription prescription) throws ClassNotFoundException, SQLException;
public String addPrescribedMedicines(PrescribedMedicines prescribedMedicines) throws ClassNotFoundException, SQLException;
public String addTest(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException;
}
