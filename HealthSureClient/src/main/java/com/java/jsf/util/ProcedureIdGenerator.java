package com.java.jsf.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ProcedureIdGenerator {
    static SessionFactory sessionFactory;

    static {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public static String generateNewProcedureId() {
        Session session = null;
        String newProcedureId = "PROC001";

        try {
            session = sessionFactory.openSession();
            session.flush(); 
            session.clear(); // Clear first-level cache

            Query query = session.createQuery("SELECT p.procedureId FROM MedicalProcedure p ORDER BY p.procedureId DESC");
            query.setMaxResults(1);
            query.setCacheable(false);
            List<String> procedureIds = query.list();

            if (procedureIds != null && !procedureIds.isEmpty()) {
                String latestProcedureId = procedureIds.get(0);

                if (latestProcedureId != null && latestProcedureId.startsWith("PROC")) {
                    String numericPart = latestProcedureId.substring(4);

                    try {
                        int currentNum = Integer.parseInt(numericPart);
                        currentNum++;
                        newProcedureId = (currentNum <= 999)
                                ? "PROC" + String.format("%03d", currentNum)
                                : "PROC999";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            if (session != null) session.close();
        }
        System.out.println("________________________generated id is "+newProcedureId);
        return newProcedureId;
    }

    public static String generateNewPrescriptionId() {
        Session session = null;
        String newPrescriptionId = "PRESC001";

        try {
            session = sessionFactory.openSession();
            session.clear();

            Query query = session.createQuery("SELECT pr.prescriptionId FROM Prescription pr ORDER BY pr.prescriptionId DESC");
            query.setMaxResults(1);
            query.setCacheable(false);

            String latestPrescriptionId = (String) query.uniqueResult();

            if (latestPrescriptionId != null && latestPrescriptionId.startsWith("PRESC")) {
                String numericPart = latestPrescriptionId.substring(5);

                try {
                    int currentNum = Integer.parseInt(numericPart);
                    currentNum++;
                    newPrescriptionId = (currentNum <= 999)
                            ? "PRESC" + String.format("%03d", currentNum)
                            : "PRESC999";
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            if (session != null) session.close();
        }

        return newPrescriptionId;
    }

    public static String generateNewPrescribedMedicineId() {
        Session session = null;
        String newPrescribedMedicineId = "PMED001";

        try {
            session = sessionFactory.openSession();
            session.clear();

            Query query = session.createQuery("SELECT pm.prescribedId FROM PrescribedMedicines pm ORDER BY pm.prescribedId DESC");
            query.setMaxResults(1);
            query.setCacheable(false);

            String latestPrescribedId = (String) query.uniqueResult();

            if (latestPrescribedId != null && latestPrescribedId.startsWith("PMED")) {
                String numericPart = latestPrescribedId.substring(4);

                try {
                    int currentNum = Integer.parseInt(numericPart);
                    currentNum++;
                    newPrescribedMedicineId = (currentNum <= 999)
                            ? "PMED" + String.format("%03d", currentNum)
                            : "PMED999";
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            if (session != null) session.close();
        }

        return newPrescribedMedicineId;
    }

    public static String generateNewProcedureTestId() {
        Session session = null;
        String newProcedureTestId = "PTEST001";

        try {
            session = sessionFactory.openSession();
            session.clear();

            Query query = session.createQuery("SELECT pt.testId FROM ProcedureTest pt ORDER BY pt.testId DESC");
            query.setMaxResults(1);
            query.setCacheable(false);
            String latestTestId = (String) query.uniqueResult();

            if (latestTestId != null && latestTestId.startsWith("PTEST")) {
                String numericPart = latestTestId.substring(5);

                try {
                    int currentNum = Integer.parseInt(numericPart);
                    currentNum++;
                    newProcedureTestId = (currentNum <= 999)
                            ? "PTEST" + String.format("%03d", currentNum)
                            : "PTEST999";
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            if (session != null) session.close();
        }

        return newProcedureTestId;
    }
}
