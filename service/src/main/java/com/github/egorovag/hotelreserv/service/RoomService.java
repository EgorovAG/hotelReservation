package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.api.IroomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.service.api.IroomService;

public class RoomService implements IroomService {

    private IroomDao iroomDao = RoomDao.getInstance();
    private static volatile IroomService instance;

    public static IroomService getInstance() {
        IroomService localInstance = instance;
        if (localInstance == null) {
            synchronized (IroomService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoomService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int readRoomIdService(int numOfSeats, String classOfApp) {
        return iroomDao.readRoom_IdDao(numOfSeats, classOfApp);
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