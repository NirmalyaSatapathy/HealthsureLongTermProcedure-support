package com.java.jsf.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionHelper {
    private static final SessionFactory sessionFactory;
 
    static {
        try {
            sessionFactory = new Configuration()
                .configure() // loads hibernate.cfg.xml
                .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}