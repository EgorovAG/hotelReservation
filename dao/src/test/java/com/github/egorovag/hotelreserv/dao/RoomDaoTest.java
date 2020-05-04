package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultRoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomDaoTest {
    private RoomDao roomDao = DefaultRoomDao.getInstance();
    private Integer numOfSeats = 2;
    private Integer roomId = 4;

    @Test
    void testReadRoomIdDao() {
        String classOfApp = "ECONOM";
        Integer resultRoomId = roomDao.readRoomIdDao(numOfSeats, classOfApp);
        Assertions.assertEquals(roomId, resultRoomId);
    }

    @Test
    void testReadRoomByIdDao() {
        Room room = roomDao.readRoomByIdDao(roomId);
        Assertions.assertEquals(numOfSeats, room.getNumOfSeats());
        Assertions.assertEquals(roomId, room.getId());
    }
}