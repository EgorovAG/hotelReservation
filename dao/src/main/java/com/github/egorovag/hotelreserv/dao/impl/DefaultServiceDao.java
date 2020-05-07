package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.ServiceDao;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.List;

public class DefaultServiceDao implements ServiceDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultServiceDao.class);
    private static volatile ServiceDao instance;

    public static ServiceDao getInstance() {
        ServiceDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultServiceDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Service> readService() {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            List<Service> serviceList = session.createQuery("From Service")
                    .getResultList();
            session.getTransaction().commit();
            log.info("List<Service> readed: {}", serviceList);
            return serviceList;
        } catch (HibernateException e) {
            log.error("Fail to read List<Service>", e);
            return null;
        }
    }
}
