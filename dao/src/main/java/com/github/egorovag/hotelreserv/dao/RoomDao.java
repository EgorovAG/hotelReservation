package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;

import java.sql.SQLException;

public interface RoomDao {

    Room readRoomByNumOfSeatsAndClassOfApDao(int numOfSeats, ClassRoom classOfAp);
}
