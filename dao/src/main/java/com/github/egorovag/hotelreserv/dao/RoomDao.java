package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;

import java.sql.SQLException;

public interface RoomDao {
    int readRoomIdDao(int numOfSeats, ClassRoom classOfApp);
    Room readRoomByIdDao(int id);
    Room readRoomByNumOfSeatsAndClassOfApDao( int numOfSeats, ClassRoom classOfAp);
//    Room getRoomDao();
}
