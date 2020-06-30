package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import net.sf.ehcache.CacheManager;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class RoomDaoTest {
    @Autowired
    RoomDao roomDao;
    @Autowired
    SessionFactory sessionFactory;

    private Integer numOfSeats = 2;
    private Integer roomId = 4;

    @Test
    void testReadRoomByNumOfSeatsAndClassOfApDao() {

        Room room = roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, ClassRoom.ECONOM);
        sessionFactory.getCurrentSession().flush();
        Assertions.assertEquals(roomId, room.getId());
    }

    @Test
    void testCashL2() {
        Room room = roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, ClassRoom.ECONOM);
        Room room1 = roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, ClassRoom.ECONOM);
        Room room2 = roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, ClassRoom.ECONOM);
        Room room3 = roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, ClassRoom.ECONOM);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("com.github.egorovag.hotelreserv.dao.entity.RoomEntity").getSize();
        Assertions.assertTrue(size > 0);
    }
}