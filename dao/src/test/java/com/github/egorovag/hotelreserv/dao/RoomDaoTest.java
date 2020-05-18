package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultRoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomDaoTest {
    private RoomDao roomDao = DefaultRoomDao.getInstance();
    private Integer numOfSeats = 2;
    private Integer roomId = 4;

    @Test
    void testReadRoomByNumOfSeatsAndClassOfApDao() {
        Room room = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        Assertions.assertEquals(roomId, room.getId());
    }

    @Test
    void testCashL2(){
        Room room = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        Room room1 = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        Room room2 = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        Room room3 = roomDao.readRoomByNumOfSeatsAndClassOfApDao ( numOfSeats, ClassRoom.ECONOM);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("com.github.egorovag.hotelreserv.model.Room").getSize();
        Assertions.assertTrue(size>0);
    }
}


//    @Test
//    void testReadRoomByIdDao() {
//        Room room = roomDao.readRoomByIdDao(roomId);
//        Assertions.assertEquals(numOfSeats, room.getNumOfSeats());
//        Assertions.assertEquals(roomId, room.getId());
//    }
//    @Test
//    void testReadRoomIdDao() {
//        Integer resultRoomId = roomDao.readRoomIdDao(numOfSeats, ClassRoom.ECONOM);
//        Assertions.assertEquals(roomId, resultRoomId);
//    }