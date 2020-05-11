package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultRoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomDaoTest {
    private RoomDao roomDao = DefaultRoomDao.getInstance();
    private Integer numOfSeats = 2;
    private Integer roomId = 4;

    @Test
    void testReadRoomIdDao() {
        Integer resultRoomId = roomDao.readRoomIdDao(numOfSeats, ClassRoom.ECONOM);
        Assertions.assertEquals(roomId, resultRoomId);
    }

    @Test
    void testReadRoomByIdDao() {
        Room room = roomDao.readRoomByIdDao(roomId);
        Assertions.assertEquals(numOfSeats, room.getNumOfSeats());
        Assertions.assertEquals(roomId, room.getId());
    }

    @Test
    void testReadRoomByNumOfSeatsAndClassOfApDao() {
        Room room = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        Assertions.assertEquals(roomId, room.getId());
    }
}