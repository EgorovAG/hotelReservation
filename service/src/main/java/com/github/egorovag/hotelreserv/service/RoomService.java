package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;

import java.sql.SQLException;

public interface RoomService {

    Integer readRoomIdService(int numOfSeats, ClassRoom classOfApp);
    Room readRoomByNumOfSeatsAndClassOfAp(int numOfSeats, ClassRoom classOfAp);
    Room readRoomById(int id);
//    Room getRoomDao();
}
