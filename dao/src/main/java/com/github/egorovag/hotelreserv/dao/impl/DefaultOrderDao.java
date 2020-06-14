package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.dao.converter.ClientConverter;
import com.github.egorovag.hotelreserv.dao.converter.OrderClientConverter;
import com.github.egorovag.hotelreserv.dao.converter.RoomConverter;
import com.github.egorovag.hotelreserv.dao.entity.ClientEntity;
import com.github.egorovag.hotelreserv.dao.entity.OrderClientEntity;
import com.github.egorovag.hotelreserv.dao.entity.RoomEntity;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.hibernate.type.spi.TypeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class DefaultOrderDao implements OrderDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultOrderDao.class);
    private final SessionFactory sessionFactory;

    public DefaultOrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrderClient saveOrderDao(OrderClient orderClient, Room room, Client client) {
        try {
            OrderClientEntity orderClientEntity = OrderClientConverter.toEntity(orderClient);
            RoomEntity roomEntity = RoomConverter.toEntity(room);
            ClientEntity clientEntity = ClientConverter.toEntity(client);
            final Session session = sessionFactory.getCurrentSession();
            orderClientEntity.setRoomEntity(roomEntity);
            orderClientEntity.setClientEntity(clientEntity);
            int id = (int) session.save(orderClientEntity);
            orderClientEntity = session.get(OrderClientEntity.class, id);
            log.info("Order: {} saved", orderClient);
            return OrderClientConverter.fromEntity(orderClientEntity);
        } catch (HibernateException e) {
            log.error("Fail to save Order: {}", orderClient, e);
            return null;
        }
    }

    @Override
    public List<OrderForAdmin> readOrderListForAdminDao() {
        Properties params = new Properties();
        params.put("enumClass", "com.github.egorovag.hotelreserv.model.enums.Condition");
        params.put("useNamed", true);
        TypeConfiguration typeConfiguration = new TypeConfiguration();
        Type myEnumType = new TypeLocatorImpl(new TypeResolver(typeConfiguration, new TypeFactory(typeConfiguration))).custom(EnumType.class, params);
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<OrderForAdmin> orderForAdmins = session.createNativeQuery("select oC.order_id as id, c.firstName, " +
                    "c.secondName, c.email, c.phone, oC.client_id as clientId, oC.startDate, oC.endDate, oC.conditions as 'condition' " +
                    "from client c join orderClient oC on c.id = oC.client_id")
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("phone", StandardBasicTypes.STRING)
                    .addScalar("clientId", StandardBasicTypes.INTEGER)
                    .addScalar("startDate", LocalDateType.INSTANCE)
                    .addScalar("endDate", LocalDateType.INSTANCE)
                    .addScalar("condition", myEnumType)
                    .setResultTransformer(Transformers.aliasToBean(OrderForAdmin.class))
                    .list();
            log.info("List<OrderForAdmin> readed");
            return orderForAdmins;
        } catch (HibernateException e) {
            log.error("Fail to read List<OrderForAdmin>", e);
        }
        return null;
    }

    @Override
    public List<OrderForClient> readOrderForClientByClientIdDao(int id) {
        Properties params = new Properties();
        params.put("enumClass", "com.github.egorovag.hotelreserv.model.enums.Condition");
        params.put("useNamed", true);
        TypeConfiguration typeConfiguration = new TypeConfiguration();
        Type myEnumType = new TypeLocatorImpl(new TypeResolver(typeConfiguration,
                new TypeFactory(typeConfiguration))).custom(EnumType.class, params);
        Properties params2 = new Properties();
        params2.put("enumClass", "com.github.egorovag.hotelreserv.model.enums.ClassRoom");
        params2.put("useNamed", true);
        TypeConfiguration typeConfiguration2 = new TypeConfiguration();
        Type myEnumType2 = new TypeLocatorImpl(new TypeResolver(typeConfiguration2,
                new TypeFactory(typeConfiguration2))).custom(EnumType.class, params2);
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<OrderForClient> orderForClients = session.createNativeQuery("select oc.order_id as id, oc.startDate, oc.endDate, r.numOfSeats, r.classOfAp, r.price, oc.conditions as 'condition' from orderclient as oc join room r on oc.room_id = r.id where oc.client_id= :clientId")
                    .setParameter("clientId", id)
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("startDate", LocalDateType.INSTANCE)
                    .addScalar("endDate", LocalDateType.INSTANCE)
                    .addScalar("numOfSeats", StandardBasicTypes.INTEGER)
                    .addScalar("classOfAp", myEnumType2)
                    .addScalar("price", StandardBasicTypes.INTEGER)
                    .addScalar("condition", myEnumType)
                    .setResultTransformer(Transformers.aliasToBean(OrderForClient.class))
                    .list();
            log.info("OrderForClient with id: {} readed", id);
            return orderForClients;
        } catch (HibernateException e) {
            log.error("Fail read OrderForClient with id: {}", id, e);
            return null;
        }
    }

    @Override
    public List<OrderClient> readOrderClientListByClientIdDao(int clientId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<OrderClientEntity> orderClientEntities = session.createQuery("from OrderClientEntity as OC where OC.clientId = :clientId ")
                    .setParameter("clientId", clientId)
                    .getResultList();
            log.info("OrderClient with clientId: {} readed", clientId);
            return orderClientEntities.stream()
                    .map(OrderClientConverter::fromEntity)
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            log.error("Fail read OrderClient with clientId: {}", clientId, e);
            return null;
        }
    }

    @Override
    public List<OrderClient> readOrderClientListDao() {
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<OrderClientEntity> orderClientEntities = session.createQuery("from OrderClientEntity")
                    .setCacheable(true)
                    .getResultList();
            log.info("OrderClient readed");
            return orderClientEntities.stream()
                    .map(OrderClientConverter::fromEntity)
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            log.error("Fail read OrderClient", e);
            return null;
        }
    }

    @Override
    public boolean updateOrderListDao(int orderId, Condition condition) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClientEntity orderClientEntity = session.get(OrderClientEntity.class, orderId);
            orderClientEntity.setCondition(condition);
            session.saveOrUpdate(orderClientEntity);
            log.info("Condition: {} orderclient with order_id: {} updated", condition, orderId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to update condition: {} orderclient with order_id: {}", condition, orderId, e);
            return false;
        }
    }

    @Override
    public int readPriceForRoomByOrderIdDao(int orderId) {
        int price = 0;
        try {
            final Session session = sessionFactory.getCurrentSession();
            price = (int) session.createNativeQuery("select r.price  from room r join orderClient oC on r.id = oC.room_id where oC.order_id = :orderId")
                    .setParameter("orderId", orderId)
                    .getSingleResult();
            log.info("Price with orderId: {} readed", orderId);
            return price;
        } catch (HibernateException e) {
            //  разобраться что возвращает price или ОШИБКУ!!!!!
            log.error("Fail read price with orderId: {}", orderId, e);
            return price;
        }
    }

    @Override
    public boolean deleteOrderByOrderIdDao(int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClientEntity orderClientEntity = session.createQuery("select oC from OrderClientEntity oC where order_id = :orderId", OrderClientEntity.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
            session.delete(orderClientEntity);
            log.info("orderclient with client_id:{} deleted", orderId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete orderclient with client_id:{}", orderId, e);
            return false;
        }
    }

    @Override
    public int checkIdOrderByClientOrderDao(int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClientEntity orderClientEntity = session.get(OrderClientEntity.class, orderId);
            log.info("ClientId with orderId:{} readed ", orderId);
            return orderClientEntity.getClientId();
        } catch (HibernateException e) {
            log.error("Fail read ClientId with orderId:{} ", orderId, e);
            return 0;
        }
    }

    @Override
    public Condition readConditionByOrderIdDao(int orderId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            OrderClientEntity orderClientEntity = session.get(OrderClientEntity.class, orderId);
            log.info("Condition with orderId: {} readed", orderId);
            return orderClientEntity.getCondition();
        } catch (HibernateException e) {
            log.error("Fail read condition with orderId: {}", orderId, e);
            return null;
        }
    }
}