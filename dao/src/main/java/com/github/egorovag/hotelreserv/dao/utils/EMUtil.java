package com.github.egorovag.hotelreserv.dao.utils;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory("com.github.egorovag.hotelreserv.dao");
    }
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager().unwrap(Session.class);
    }

    public static void close() {
        emFactory.close();
    }
}
