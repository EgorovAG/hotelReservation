package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.dao.utils.EMUtil;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultServiceHotelDao implements ServiceHotelDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultServiceHotelDao.class);
    private static volatile ServiceHotelDao instance;

    public static ServiceHotelDao getInstance() {
        ServiceHotelDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceHotelDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultServiceHotelDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Service readServiceByTypeOfServiceDao(String typeOfService) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Service service = session.createQuery("select S from Service as S where S.typeOfService = :typeOfService", Service.class)
                    .setParameter("typeOfService", typeOfService)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Service by typeOfService: {} readed", typeOfService);
            return service;
        } catch (HibernateException e) {
            log.error("Fail to read Service by typeOfService: {} ", typeOfService, e);
            return null;
        }
    }

    @Override
    public boolean saveServiceListForOrderDao(List<Service> serviceList, int orderId) {
        try (Session session = SFUtil.getSession()) {
            ArrayList<Service> serviceArrayList = new ArrayList<>(serviceList);
            session.beginTransaction();
            OrderClient orderClient = session.get(OrderClient.class, orderId);
            orderClient.getServices().addAll(serviceArrayList);
            session.saveOrUpdate(orderClient);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Service> readServiceListByOrderIdDao(int orderId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            OrderClient orderClient = session.get(OrderClient.class, orderId);
            List<Service> serviceList = orderClient.getServices();
            session.getTransaction().commit();
            return serviceList;
        } catch (HibernateException e) {
            return null;
        }
    }
}










//    @Override
//    public List<Service> readServiceHotelList() {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            List<Service> serviceList = session.createQuery("From Service")
//                    .getResultList();
//            session.getTransaction().commit();
//            log.info("List<Service> readed: {}", serviceList);
//            return serviceList;
//        } catch (HibernateException e) {
//            log.error("Fail to read List<Service>", e);
//            return null;
//        }
//    }


