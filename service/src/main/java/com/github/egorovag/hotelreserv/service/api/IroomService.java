package com.github.egorovag.hotelreserv.service.api;

import com.github.egorovag.hotelreserv.model.Room;

import java.sql.SQLException;

public interface IroomService {

    int readRoomIdService(int numOfSeats, String classOfApp);
    Room readRoomByIdService(int id);
//    Room getRoomDao();
}
