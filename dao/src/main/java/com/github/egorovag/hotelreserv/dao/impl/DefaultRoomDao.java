package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.converter.RoomConverter;
import com.github.egorovag.hotelreserv.dao.entity.RoomEntity;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRoomDao implements RoomDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultRoomDao.class);
    private final SessionFactory sessionFactory;

    public DefaultRoomDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Room readRoomByNumOfSeatsAndClassOfApDao(int numOfSeats, ClassRoom classOfAp) {
        try {
            Session session = sessionFactory.getCurrentSession();
            RoomEntity roomEntity = session.createQuery("SELECT r from RoomEntity r where numOfSeats = : numOfSeats and  classOfAp = :classOfAp", RoomEntity.class)
                    .setParameter("numOfSeats", numOfSeats)
                    .setParameter("classOfAp", classOfAp)
                    .setCacheable(true)
                    .getSingleResult();
            log.info("Room_id with numOfSeats: {} and classOfApp: {} readed", numOfSeats, classOfAp);
            return RoomConverter.fromEntity(roomEntity);
        } catch (HibernateException e) {
            log.error("Fail to read room_id with numOfSeats: {} and classOfApp: {} ", numOfSeats, classOfAp, e);
            return null;
        }
    }
}