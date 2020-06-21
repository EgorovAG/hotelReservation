package com.github.egorovag.hotelreserv.dao.utils;

import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SFUtil {
    private static final SessionFactory sessionFactory;
    static ResourceBundle resource = ResourceBundle.getBundle("db");
    private static String url = resource.getString("url");
    private static String user = resource.getString("user");
    private static String pass = resource.getString("password");

    /*
        SessionFactory initialization
     */
    static {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, url);
        settings.put(Environment.USER, user);
        settings.put(Environment.PASS, pass);
        settings.put(Environment.HBM2DDL_AUTO, "validate");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.USE_SQL_COMMENTS, "true");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put(Environment.ISOLATION, "2");
        settings.put(Environment.STORAGE_ENGINE, "innodb");

        settings.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        settings.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        settings.put(Environment.USE_QUERY_CACHE, "true");
        settings.put(Environment.AUTO_EVICT_COLLECTION_CACHE, "true");
//        <property name = "net.sf.ehcache.configurationResourceName" value = "ehcache.xml" / >

        // Apply settings
        serviceRegistryBuilder.applySettings(settings);
        // Create registry
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(AuthUser.class);
        sources.addAnnotatedClass(Client.class);
        sources.addAnnotatedClass(OrderClient.class);
        sources.addAnnotatedClass(Room.class);
        sources.addAnnotatedClass(BlackList.class);
        sources.addAnnotatedClass(ServiceHotel.class);
        sources.addAnnotatedClass(AuthUserWithClientDTO.class);
        sources.addAnnotatedClass(BlackListUsersDTO.class);
        sources.addAnnotatedClass(OrderForAdminDTO.class);
        sources.addAnnotatedClass(OrderForClientDTO.class);
        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
