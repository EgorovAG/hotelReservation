package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.dao.converter.ServiceHotelConverter;
import com.github.egorovag.hotelreserv.dao.entity.OrderClientEntity;
import com.github.egorovag.hotelreserv.dao.entity.ServiceHotelEntity;
import com.github.egorovag.hotelreserv.model.ServiceHotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultServiceHotelDao implements ServiceHotelDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultServiceHotelDao.class);
    private final SessionFactory sessionFactory;

    public DefaultServiceHotelDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ServiceHotel readServiceByTypeOfServiceDao(String typeOfService) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            ServiceHotelEntity serviceHotelEntity = session.createQuery("select S from ServiceHotelEntity as S where S.typeOfService = :typeOfService", ServiceHotelEntity.class)
                    .setParameter("typeOfService", typeOfService)
                    .getSingleResult();
            log.info("Service by typeOfService: {} readed", typeOfService);
            return ServiceHotelConverter.fromEntity(serviceHotelEntity);
        } catch (HibernateException e) {
            log.error("Fail to read Service by typeOfService: {} ", typeOfService, e);
            return null;
        }
    }

    @Override
    public boolean saveServiceListForOrderDao(List<ServiceHotel> serviceList, int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<ServiceHotelEntity> serviceHotelEntities = new ArrayList<>(serviceList.stream()
                    .map(ServiceHotelConverter::toEntity)
                    .collect(Collectors.toList()));
            OrderClientEntity orderClientEntity = session.get(OrderClientEntity.class, orderId);
            orderClientEntity.getServiceHotelEntities().addAll(serviceHotelEntities);
            session.saveOrUpdate(orderClientEntity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<ServiceHotel> readServiceListByOrderIdDao(int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClientEntity orderClientEntity = session.get(OrderClientEntity.class, orderId);
            List<ServiceHotelEntity> serviceHotelEntities = orderClientEntity.getServiceHotelEntities();
            return serviceHotelEntities.stream().map(ServiceHotelConverter::fromEntity).collect(Collectors.toList());
        } catch (HibernateException e) {
            return null;
        }
    }
}