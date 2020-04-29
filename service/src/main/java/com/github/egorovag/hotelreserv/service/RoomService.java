package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Room;

import java.sql.SQLException;

public interface RoomService {

    int readRoomIdService(int numOfSeats, String classOfApp);
    Room readRoomByIdService(int id);
//    Room getRoomDao();
}
