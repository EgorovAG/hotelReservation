package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultServiceHotelDao implements ServiceHotelDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultServiceHotelDao.class);
    private final SessionFactory sessionFactory;

    public DefaultServiceHotelDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Service readServiceByTypeOfServiceDao(String typeOfService) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            Service service = session.createQuery("select S from Service as S where S.typeOfService = :typeOfService", Service.class)
                    .setParameter("typeOfService", typeOfService)
                    .getSingleResult();
            log.info("Service by typeOfService: {} readed", typeOfService);
            return service;
        } catch (HibernateException e) {
            log.error("Fail to read Service by typeOfService: {} ", typeOfService, e);
            return null;
        }
    }

    @Override
    public boolean saveServiceListForOrderDao(List<Service> serviceList, int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            ArrayList<Service> serviceArrayList = new ArrayList<>(serviceList);
            OrderClient orderClient = session.get(OrderClient.class, orderId);
            orderClient.getServices().addAll(serviceArrayList);
            session.saveOrUpdate(orderClient);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Service> readServiceListByOrderIdDao(int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClient orderClient = session.get(OrderClient.class, orderId);
            List<Service> serviceList = orderClient.getServices();
            return serviceList;
        } catch (HibernateException e) {
            return null;
        }
    }
}