package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultRoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.service.RoomService;

public class DefaultRoomService implements RoomService {

    private RoomDao iroomDao = DefaultRoomDao.getInstance();
    private static volatile RoomService instance;

    public static RoomService getInstance() {
        RoomService localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRoomService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Integer readRoomIdService(int numOfSeats, String classOfApp) {
        return iroomDao.readRoomIdDao(numOfSeats, classOfApp);
    }

    @Override
    public Room readRoomByIdService(int id) {
        return iroomDao.readRoomByIdDao(id);
    }

//    @Override
//    public Room getRoomDao() {
//        return iroomDao.getRoomDao();
//    }
}