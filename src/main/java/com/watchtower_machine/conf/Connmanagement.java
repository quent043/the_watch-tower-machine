package com.watchtower_machine.conf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connmanagement {
    private static SessionFactory sessionFactory;
    private static Session session;



    private Connmanagement() {
        if(sessionFactory == null) {
            System.out.println("*** No actual connection, initiating new connection... ***");
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        if (session == null) {
            System.out.println("*** No actual session, initiating new session... ***");
            session = sessionFactory.openSession();
            System.out.println("*** Session initiated *** " + session.toString());
        }
        System.out.println("*** Connection initiated ***");
    }

    public static void createConnection() {
        if (sessionFactory == null || session == null) {
            new Connmanagement();
        } else {
            System.out.println("*** Connection already initiated ***");
        }
    }

    public static void closeSession() {
        session.close();
        session = null;
        System.out.println("*** Session closed ***");
    }

    public static void closeConnection() {
        session.close();
        sessionFactory.close();
        session = null;
        sessionFactory = null;
        System.out.println("*** Connection terminated ***");
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Connmanagement.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Connmanagement.session = session;
    }
}
