package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultRoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.service.RoomService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultRoomService implements RoomService {

    private final RoomDao roomDao;

    public DefaultRoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional
    public Room readRoomByNumOfSeatsAndClassOfAp(int numOfSeats, ClassRoom classOfAp) {
        return roomDao.readRoomByNumOfSeatsAndClassOfApDao(numOfSeats, classOfAp);
    }
}



//    @Override
//    public Room readRoomById(int id) {
//        return roomDao.readRoomByIdDao(id);
//    }
//    @Override
//    public Integer readRoomIdService(int numOfSeats, ClassRoom classOfApp) {
//        return roomDao.readRoomIdDao(numOfSeats, classOfApp);
//    }
//    @Override
//    public Room getRoomDao() {
//        return iroomDao.getRoomDao();
//    }
