package com.github.egorovag.hotelreserv.dao.api;

import com.github.egorovag.hotelreserv.model.Room;

import java.sql.SQLException;

public interface IroomDao {
    int readRoom_IdDao(int numOfSeats, String classOfApp);
    Room readRoomByIdDao(int id);
//    Room getRoomDao();
}
