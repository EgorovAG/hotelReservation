//package com.github.egorovag.hotelreserv.dao;
//
//import com.github.egorovag.hotelreserv.dao.IroomDao;
//import com.github.egorovag.hotelreserv.model.Room;
//import jdk.nashorn.internal.ir.annotations.Ignore;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//class RoomDaoTest {
//    private IroomDao iroomDao = RoomDao.getInstance();
//    private int numOfSeats = 2;
//    private String classOfApp = "ECONOM";
//    private Long room_id = 4;
//
//    @Test
//    void testReadRoom_IdDao() {
//        Long resultRoom_id =iroomDao.readRoom_IdDao(numOfSeats, classOfApp);
//        Assertions.assertEquals(room_id, resultRoom_id);
//    }
//
//    @Test
//    void testReadRoomByIdDao() {
//        Room room = iroomDao.readRoomByIdDao(room_id);
//        Assertions.assertEquals(numOfSeats, room.getNumOfSeats());
//        Assertions.assertEquals(room_id, room.getId());
//    }
//}