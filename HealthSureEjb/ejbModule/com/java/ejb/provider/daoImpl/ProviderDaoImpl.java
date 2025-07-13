package com.java.ejb.provider.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.ejb.provider.dao.ProviderDao;
import com.java.ejb.provider.model.Appointment;
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
import com.java.ejb.util.ConnectionHelper;

public class ProviderDaoImpl implements ProviderDao{

	public String addMedicalProcedure(MedicalProcedure medicalProcedure) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();
	    String sql = "INSERT INTO medical_procedure (" +
	                 "procedure_id, appointment_id, h_id, provider_id, doctor_id, " +
	                 "scheduled_date, procedure_date, from_date, to_date, " +
	                 "diagnosis, recommendations, procedure_status, procedure_type) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    PreparedStatement pst = con.prepareStatement(sql);

	    pst.setString(1, medicalProcedure.getProcedureId());
	    pst.setString(2, medicalProcedure.getAppointment().getAppointmentId());
	    pst.setString(3, medicalProcedure.getRecipient().gethId());
	    pst.setString(4, medicalProcedure.getProvider().getProviderId());
	    pst.setString(5, medicalProcedure.getDoctor().getDoctorId());

	    // Handle different procedure statuses
	    switch (medicalProcedure.getProcedureStatus()) {
	        case SCHEDULED:
	            pst.setDate(6, new java.sql.Date(medicalProcedure.getScheduledDate().getTime())); // scheduled_date
	            pst.setDate(7, null);      // procedure_date
	            pst.setTimestamp(8, null); // from_date
	            pst.setTimestamp(9, null); // to_date
	            break;

	        case COMPLETED:
	            pst.setDate(6, null); // scheduled_date
	            pst.setDate(7, new java.sql.Date(medicalProcedure.getProcedureDate().getTime())); // procedure_date
	            pst.setTimestamp(8, null); // from_date
	            pst.setTimestamp(9, null); // to_date
	            break;

	        case IN_PROGRESS:
	            pst.setDate(6, null); // scheduled_date
	            pst.setDate(7, null); // procedure_date
	            pst.setTimestamp(8, new java.sql.Timestamp(medicalProcedure.getFromDate().getTime())); // from_date = today
	            if (medicalProcedure.getToDate() != null) {
	                pst.setTimestamp(9, new java.sql.Timestamp(medicalProcedure.getToDate().getTime()));
	            } else {
	                pst.setTimestamp(9, null);
	            }
	            break;

	        default:
	            throw new IllegalArgumentException("Unsupported ProcedureStatus: " + medicalProcedure.getProcedureStatus());
	    }

	    pst.setString(10, medicalProcedure.getDiagnosis());
	    pst.setString(11, medicalProcedure.getRecommendations());
	    pst.setString(12, medicalProcedure.getProcedureStatus().name());
	    pst.setString(13, medicalProcedure.getType().name());

	    pst.executeUpdate();
	    pst.close();
	    con.close();

	    return "inserted";
	}



	@Override
	public String addPrescription(Prescription prescription) throws SQLException, ClassNotFoundException {
		System.out.println("remote prescription called");
	    Connection con = ConnectionHelper.getConnection();
	    String sql = "INSERT INTO prescription (" +
	                 "prescription_id, procedure_id, h_id, provider_id, doctor_id, " +
	                 "written_on, start_date, end_date, created_at) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    PreparedStatement pst = con.prepareStatement(sql);

	    pst.setString(1, prescription.getPrescriptionId());
	    pst.setString(2, prescription.getProcedure().getProcedureId());
	    pst.setString(3, prescription.getRecipient().gethId());
	    pst.setString(4, prescription.getProvider().getProviderId());
	    pst.setString(5, prescription.getDoctor().getDoctorId());

	    pst.setTimestamp(6, prescription.getWrittenOn() != null
	            ? new java.sql.Timestamp(prescription.getWrittenOn().getTime())
	            : new java.sql.Timestamp(System.currentTimeMillis()));

	    pst.setTimestamp(7, prescription.getStartDate() != null
	            ? new java.sql.Timestamp(prescription.getStartDate().getTime())
	            : null);

	    pst.setTimestamp(8, prescription.getEndDate() != null
	            ? new java.sql.Timestamp(prescription.getEndDate().getTime())
	            : null);

	    pst.setTimestamp(9, prescription.getCreatedAt() != null
	            ? new java.sql.Timestamp(prescription.getCreatedAt().getTime())
	            : new java.sql.Timestamp(System.currentTimeMillis()));

	    pst.executeUpdate();
	    pst.close();
	    con.close();
	    System.out.println("added and returning from remote prescription");
	    return "inserted";
	}

	@Override
	public String addPrescribedMedicines(PrescribedMedicines prescribedMedicines) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();

	    String sql = "INSERT INTO prescribed_medicines (" +
	                 "prescribed_id, prescription_id, medicine_name, dosage, duration, notes, " +
	                 "start_date, end_date, created_at,medicine_type) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	    PreparedStatement pst = con.prepareStatement(sql);

	    pst.setString(1, prescribedMedicines.getPrescribedId());
	    pst.setString(2, prescribedMedicines.getPrescription().getPrescriptionId());
	    pst.setString(3, prescribedMedicines.getMedicineName());
	    pst.setString(4, prescribedMedicines.getDosage());
	    pst.setString(5, prescribedMedicines.getDuration());
	    pst.setString(6, prescribedMedicines.getNotes());

	    pst.setTimestamp(7, prescribedMedicines.getStartDate() != null
	            ? new java.sql.Timestamp(prescribedMedicines.getStartDate().getTime())
	            : null);

	    pst.setTimestamp(8, prescribedMedicines.getEndDate() != null
	            ? new java.sql.Timestamp(prescribedMedicines.getEndDate().getTime())
	            : null);

	    pst.setTimestamp(9, prescribedMedicines.getCreatedAt() != null
	            ? new java.sql.Timestamp(prescribedMedicines.getCreatedAt().getTime())
	            : new java.sql.Timestamp(System.currentTimeMillis()));
	    pst.setString(10, prescribedMedicines.getType().name());
	    pst.executeUpdate();
	    pst.close();
	    con.close();

	    return "inserted";
	}

	



	@Override
	public String addTest(ProcedureTest procedureTest) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();

	    String sql = "INSERT INTO prescribed_tests (" +
	                 "test_id, prescription_id, test_name, test_date, result_summary, created_at) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    PreparedStatement pst = con.prepareStatement(sql);

	    pst.setString(1, procedureTest.getTestId());
	    pst.setString(2, procedureTest.getPrescription().getPrescriptionId());
	    pst.setString(3, procedureTest.getTestName());

	    // Convert testDate (java.util.Date) to java.sql.Date
	    if (procedureTest.getTestDate() != null) {
	        pst.setDate(4, new java.sql.Date(procedureTest.getTestDate().getTime()));
	    } else {
	        pst.setDate(4, new java.sql.Date(System.currentTimeMillis())); // fallback
	    }

	    pst.setString(5, procedureTest.getResultSummary());

	    if (procedureTest.getCreatedAt() != null) {
	        pst.setTimestamp(6, new java.sql.Timestamp(procedureTest.getCreatedAt().getTime()));
	    } else {
	        pst.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
	    }

	    pst.executeUpdate();
	    pst.close();
	    con.close();

	    return "inserted";
	}
	@Override
	public String addProcedureDailyLog(ProcedureDailyLog log) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();

	    String sql = "INSERT INTO procedure_daily_log (" +
	                 "log_id, procedure_id, log_date, vitals, notes, created_at) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setString(1, log.getLogId());
	    pst.setString(2, log.getMedicalProcedure().getProcedureId());

	    pst.setDate(3, log.getLogDate() != null
	            ? new java.sql.Date(log.getLogDate().getTime())
	            : new java.sql.Date(System.currentTimeMillis()));

	    pst.setString(4, log.getVitals());
	    pst.setString(5, log.getNotes());

	    pst.setTimestamp(6, log.getCreatedAt() != null
	            ? new java.sql.Timestamp(log.getCreatedAt().getTime())
	            : new java.sql.Timestamp(System.currentTimeMillis()));

	    pst.executeUpdate();
	    pst.close();
	    con.close();

	    return "inserted";
	}

	@Override
	public  String generateNewProcedureId() throws SQLException, ClassNotFoundException {
        String newProcedureId = "PROC001";

        Connection con = ConnectionHelper.getConnection();
        String sql = "SELECT procedure_id FROM medical_procedure ORDER BY procedure_id DESC LIMIT 1";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String latestId = rs.getString(1);
            if (latestId != null && latestId.startsWith("PROC")) {
                int currentNum = Integer.parseInt(latestId.substring(4));
                currentNum++;
                newProcedureId = (currentNum <= 999)
                        ? "PROC" + String.format("%03d", currentNum)
                        : "PROC999";
            }
        }

        rs.close();
        pst.close();
        con.close();

        System.out.println("________________________generated id is " + newProcedureId);
        return newProcedureId;
    }
	@Override
    public String generateNewPrescriptionId() throws SQLException, ClassNotFoundException {
        String newPrescriptionId = "PRESC001";

        Connection con = ConnectionHelper.getConnection();
        String sql = "SELECT prescription_id FROM prescription ORDER BY prescription_id DESC LIMIT 1";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String latestId = rs.getString(1);
            if (latestId != null && latestId.startsWith("PRESC")) {
                int currentNum = Integer.parseInt(latestId.substring(5));
                currentNum++;
                newPrescriptionId = (currentNum <= 999)
                        ? "PRESC" + String.format("%03d", currentNum)
                        : "PRESC999";
            }
        }

        rs.close();
        pst.close();
        con.close();

        return newPrescriptionId;
    }
	@Override
    public  String generateNewPrescribedMedicineId() throws SQLException, ClassNotFoundException {
        String newPrescribedMedicineId = "PMED001";

        Connection con = ConnectionHelper.getConnection();
        String sql = "SELECT prescribed_id FROM prescribed_medicines ORDER BY prescribed_id DESC LIMIT 1";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String latestId = rs.getString(1);
            if (latestId != null && latestId.startsWith("PMED")) {
                int currentNum = Integer.parseInt(latestId.substring(4));
                currentNum++;
                newPrescribedMedicineId = (currentNum <= 999)
                        ? "PMED" + String.format("%03d", currentNum)
                        : "PMED999";
            }
        }

        rs.close();
        pst.close();
        con.close();

        return newPrescribedMedicineId;
    }
	@Override
    public  String generateNewProcedureTestId() throws SQLException, ClassNotFoundException {
        String newProcedureTestId = "PTEST001";

        Connection con = ConnectionHelper.getConnection();
        String sql = "SELECT test_id FROM prescribed_tests ORDER BY test_id DESC LIMIT 1";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String latestId = rs.getString(1);
            if (latestId != null && latestId.startsWith("PTEST")) {
                int currentNum = Integer.parseInt(latestId.substring(5));
                currentNum++;
                newProcedureTestId = (currentNum <= 999)
                        ? "PTEST" + String.format("%03d", currentNum)
                        : "PTEST999";
            }
        }

        rs.close();
        pst.close();
        con.close();

        return newProcedureTestId;
    }
	@Override
	public String generateNewProcedureLogId() throws ClassNotFoundException, SQLException {
	    String newLogId = "PLOG001";

	    Connection con = ConnectionHelper.getConnection();
	    String sql = "SELECT log_id FROM procedure_daily_log ORDER BY log_id DESC LIMIT 1";
	    PreparedStatement pst = con.prepareStatement(sql);
	    ResultSet rs = pst.executeQuery();

	    if (rs.next()) {
	        String latestId = rs.getString(1);
	        if (latestId != null && latestId.startsWith("PLOG")) {
	            int currentNum = Integer.parseInt(latestId.substring(4));
	            currentNum++;
	            newLogId = (currentNum <= 999)
	                    ? "PLOG" + String.format("%03d", currentNum)
	                    : "PLOG999";
	        }
	    }

	    rs.close();
	    pst.close();
	    con.close();

	    return newLogId;
	}
	@Override
	public List<MedicalProcedure> getScheduledProceduresByDoctor(String doctorId, String procedureId) {
	    List<MedicalProcedure> procedures = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectionHelper.getConnection();
	        StringBuilder sql = new StringBuilder(
	            "SELECT mp.procedure_id, mp.h_id, mp.provider_id, mp.doctor_id, " +
	            "mp.appointment_id, mp.scheduled_date, mp.procedure_status, " +
	            "r.first_name, r.last_name, d.doctor_name, p.provider_name " +
	            "FROM medical_procedure mp " +
	            "JOIN Recipient r ON mp.h_id = r.h_id " +
	            "JOIN Doctors d ON mp.doctor_id = d.doctor_id " +
	            "JOIN Providers p ON mp.provider_id = p.provider_id " +
	            "WHERE mp.procedure_status = ? AND mp.doctor_id = ?"
	        );

	        if (procedureId != null && !procedureId.trim().isEmpty()) {
	            sql.append(" AND mp.procedure_id = ?");
	        }

	        pst = con.prepareStatement(sql.toString());
	        pst.setString(1, ProcedureStatus.SCHEDULED.name());
	        pst.setString(2, doctorId);

	        if (procedureId != null && !procedureId.trim().isEmpty()) {
	            pst.setString(3, procedureId);
	        }

	        rs = pst.executeQuery();

	        while (rs.next()) {
	            MedicalProcedure proc = new MedicalProcedure();

	            proc.setProcedureId(rs.getString("procedure_id"));
	            proc.setScheduledDate(rs.getDate("scheduled_date"));
	            proc.setProcedureStatus(ProcedureStatus.valueOf(rs.getString("procedure_status")));

	            Appointment appointment = new Appointment();
	            appointment.setAppointmentId(rs.getString("appointment_id"));
	            proc.setAppointment(appointment);

	            Recipient recipient = new Recipient();
	            recipient.sethId(rs.getString("h_id"));
	            recipient.setFirstName(rs.getString("first_name"));
	            recipient.setLastName(rs.getString("last_name"));
	            proc.setRecipient(recipient);

	            Doctor doctor = new Doctor();
	            doctor.setDoctorId(rs.getString("doctor_id"));
	            doctor.setDoctorName(rs.getString("doctor_name"));
	            proc.setDoctor(doctor);

	            Provider provider = new Provider();
	            provider.setProviderId(rs.getString("provider_id"));
	            provider.setName(rs.getString("provider_name"));
	            proc.setProvider(provider);

	            procedures.add(proc);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (pst != null) pst.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }

	    return procedures;
	}



	@Override
	public List<MedicalProcedure> getInProgressProcedures() {
	    List<MedicalProcedure> procedures = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = ConnectionHelper.getConnection();
	        String sql = "SELECT " +
	                     "mp.procedure_id, mp.h_id, mp.provider_id, mp.doctor_id, " +
	                     "mp.appointment_id, mp.scheduled_date, mp.from_date, mp.procedure_status, " +
	                     "r.first_name, r.last_name, " +
	                     "d.doctor_name, " +
	                     "p.provider_name " +
	                     "FROM medical_procedure mp " +
	                     "JOIN Recipient r ON mp.h_id = r.h_id " +
	                     "JOIN Doctors d ON mp.doctor_id = d.doctor_id " +
	                     "JOIN Providers p ON mp.provider_id = p.provider_id " +
	                     "WHERE mp.procedure_status = ?";

	        pst = con.prepareStatement(sql);
	        pst.setString(1, ProcedureStatus.IN_PROGRESS.name());

	        rs = pst.executeQuery();

	        while (rs.next()) {
	            MedicalProcedure proc = new MedicalProcedure();

	            proc.setProcedureId(rs.getString("procedure_id"));
	            proc.setScheduledDate(rs.getDate("scheduled_date"));
	            proc.setFromDate(rs.getDate("from_date"));
	            proc.setProcedureStatus(ProcedureStatus.valueOf(rs.getString("procedure_status")));

	            // ✅ Set Appointment
	            Appointment appointment = new Appointment();
	            appointment.setAppointmentId(rs.getString("appointment_id"));
	            proc.setAppointment(appointment);

	            // ✅ Set Recipient
	            Recipient recipient = new Recipient();
	            recipient.sethId(rs.getString("h_id"));
	            recipient.setFirstName(rs.getString("first_name"));
	            recipient.setLastName(rs.getString("last_name"));
	            proc.setRecipient(recipient);

	            // ✅ Set Doctor
	            Doctor doctor = new Doctor();
	            doctor.setDoctorId(rs.getString("doctor_id"));
	            doctor.setDoctorName(rs.getString("doctor_name"));
	            proc.setDoctor(doctor);

	            // ✅ Set Provider
	            Provider provider = new Provider();
	            provider.setProviderId(rs.getString("provider_id"));
	            provider.setName(rs.getString("provider_name"));
	            proc.setProvider(provider);

	            procedures.add(proc);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (pst != null) pst.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }

	    return procedures;
	}
	@Override
	public String updateProcedureStatus(MedicalProcedure procedure) {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        System.out.println("Updating procedure status: " + procedure);

	        conn = ConnectionHelper.getConnection();

	        String sql = null;

	        // Build SQL based on the status
	        ProcedureStatus status = procedure.getProcedureStatus();

	        switch (status) {
	            case IN_PROGRESS:
	                sql = "UPDATE medical_procedure SET procedure_status = ?, from_date = ? WHERE procedure_id = ?";
	                ps = conn.prepareStatement(sql);
	                ps.setString(1, status.name());
	                ps.setDate(2, new java.sql.Date(procedure.getFromDate().getTime()));
	                ps.setString(3, procedure.getProcedureId());
	                break;

	            case COMPLETED:
	                sql = "UPDATE medical_procedure SET procedure_status = ?, to_date = ? WHERE procedure_id = ?";
	                ps = conn.prepareStatement(sql);
	                ps.setString(1, status.name());
	                ps.setDate(2, new java.sql.Date(procedure.getToDate().getTime()));
	                ps.setString(3, procedure.getProcedureId());
	                break;

	            default:
	                // For any other status, only update status (no date change)
	                sql = "UPDATE medical_procedure SET procedure_status = ? WHERE procedure_id = ?";
	                ps = conn.prepareStatement(sql);
	                ps.setString(1, status.toString());
	                ps.setString(2, procedure.getProcedureId());
	                break;
	        }

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Procedure status updated successfully.");
	            return "Procedure status updated successfully.";
	        } else {
	            return "No procedure found with ID: " + procedure.getProcedureId();
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return "Error updating procedure status: " + e.getMessage();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	@Override
	public MedicalProcedure getProcedureById(String id) {
	    MedicalProcedure procedure = null;
	    String sql = "SELECT mp.*, " +
	             "       a.appointment_id," +
	             "       r.h_id, r.first_name AS r_fname, r.last_name AS r_lname, " +
	             "       d.doctor_id, d.doctor_name AS doctor_name, " +
	             "       p.provider_id, p.provider_name AS provider_name " +
	             "FROM medical_procedure mp " +
	             "JOIN appointment a ON mp.appointment_id = a.appointment_id " +
	             "JOIN recipient r ON mp.h_id = r.h_id " +
	             "JOIN doctors d ON mp.doctor_id = d.doctor_id " +  // ✅ Correct table name
	             "JOIN providers p ON mp.provider_id = p.provider_id " + // ✅ Correct table name
	             "WHERE mp.procedure_id = ?";


	    try (Connection conn = ConnectionHelper.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, id);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                procedure = new MedicalProcedure();

	                // Basic fields
	                procedure.setProcedureId(rs.getString("procedure_id"));
	                procedure.setDiagnosis(rs.getString("diagnosis"));
	                procedure.setRecommendations(rs.getString("recommendations"));
	                procedure.setFromDate(rs.getDate("from_date"));
	                procedure.setToDate(rs.getDate("to_date"));
	                procedure.setScheduledDate(rs.getDate("scheduled_date"));
	                procedure.setProcedureDate(rs.getDate("procedure_date"));
	                procedure.setCreatedAt(rs.getTimestamp("created_at"));

	                // Enum fields
	                String status = rs.getString("procedure_status");
	                if (status != null) {
	                    procedure.setProcedureStatus(ProcedureStatus.valueOf(status.toUpperCase()));
	                }

	                String type = rs.getString("procedure_type");
	                if (type != null) {
	                    procedure.setType(ProcedureType.valueOf(type.toUpperCase()));
	                }

	                // Associated Appointment
	                Appointment appointment = new Appointment();
	                appointment.setAppointmentId(rs.getString("appointment_id"));
	                procedure.setAppointment(appointment);

	                // Associated Recipient
	                Recipient recipient = new Recipient();
	                recipient.sethId(rs.getString("h_id"));
	                recipient.setFirstName(rs.getString("r_fname"));
	                recipient.setLastName(rs.getString("r_lname"));
	                procedure.setRecipient(recipient);

	                Doctor doctor = new Doctor();
	                doctor.setDoctorId(rs.getString("doctor_id"));
	                doctor.setDoctorName(rs.getString("doctor_name")); // ✅ this is correct
	                procedure.setDoctor(doctor);


	                // Associated Provider
	                Provider provider = new Provider();
	                provider.setProviderId(rs.getString("provider_id"));
	                provider.setName(rs.getString("provider_name"));
	                procedure.setProvider(provider);
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace(); // Replace with proper logging in production
	    }

	    return procedure;
	}






}
