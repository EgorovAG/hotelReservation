package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Room;

import java.sql.SQLException;

public interface RoomDao {
    int readRoomIdDao(int numOfSeats, String classOfApp);
    Room readRoomByIdDao(int id);
//    Room getRoomDao();
}
