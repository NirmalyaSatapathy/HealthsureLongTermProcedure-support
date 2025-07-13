package com.java.jsf.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.java.ejb.provider.model.PrescribedMedicines;
import com.java.ejb.provider.model.Prescription;
import com.java.ejb.provider.model.ProcedureDailyLog;
import com.java.ejb.provider.model.ProcedureTest;

public class ProcedureIdGenerator {
    static SessionFactory sessionFactory;

    static {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public static String getNextPrescriptionId(List<Prescription> list) {
        int max = 0;

        for (Prescription p : list) {
            String id = p.getPrescriptionId(); // e.g. PRESC005
            if (id != null && id.startsWith("PRESC")) {
                try {
                    int num = Integer.parseInt(id.substring(5));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }

        int next = max + 1;
        return "PRESC" + String.format("%03d", next);
    }


    public static String getNextPrescribedMedicineId(List<PrescribedMedicines> list) {
        int max = 0;

        for (PrescribedMedicines med : list) {
            String id = med.getPrescribedId(); // e.g. PMED004
            if (id != null && id.startsWith("PMED")) {
                try {
                    int num = Integer.parseInt(id.substring(4));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }

        int next = max + 1;
        return "PMED" + String.format("%03d", next);
    }
    public static String getNextProcedureTestId(List<ProcedureTest> list) {
        int max = 0;

        for (ProcedureTest test : list) {
            String id = test.getTestId(); // e.g. PTEST007
            if (id != null && id.startsWith("PTEST")) {
                try {
                    int num = Integer.parseInt(id.substring(5));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }

        int next = max + 1;
        return "PTEST" + String.format("%03d", next);
    }
    public static String getNextProcedureLogId(List<ProcedureDailyLog> list) {
        int max = 0;

        for (ProcedureDailyLog log : list) {
            String id = log.getLogId(); // e.g. PLOG010
            if (id != null && id.startsWith("PLOG")) {
                try {
                    int num = Integer.parseInt(id.substring(4));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }

        int next = max + 1;
        return "PLOG" + String.format("%03d", next);
    }
}