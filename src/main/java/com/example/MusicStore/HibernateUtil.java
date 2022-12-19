package com.example.MusicStore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {

    private static Session session;
    private static SessionFactory sessionFactory;


    static {

        Configuration cfg = new Configuration().configure(new File("F:\\MusicStore\\src\\main\\resources\\hibernate.cfg.xml"));
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static Session configureSession() {
        session = sessionFactory.openSession();
        return session;
    }
}