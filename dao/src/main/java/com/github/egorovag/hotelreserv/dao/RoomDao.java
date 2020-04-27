package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.api.IroomDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.api.ClassRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao implements IroomDao {
    private static final Logger log = LoggerFactory.getLogger(RoomDao.class);
    private static volatile IroomDao instance;

    public static IroomDao getInstance() {
        IroomDao localInstance = instance;
        if (localInstance == null) {
            synchronized (IroomDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoomDao() ;
                }
            }
        }
        return localInstance;
    }

    @Override
    public int readRoom_IdDao(int numOfSeats, String classOfApp) {
        int id = 0;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement("select id from room where numOfSeats = ? and classOfAp =?")) {
            statement.setInt(1, numOfSeats);
            statement.setString(2,classOfApp);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    return   rs.getInt(1);
                }
            }
            log.info("Room_id with numOfSeats: {} and classOfApp: {} readed", numOfSeats , classOfApp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read room_id with numOfSeats: {} and classOfApp: {} ", numOfSeats , classOfApp, e);
        }
        return id;
    }

    @Override
    public Room readRoomByIdDao(int id) {
        Room room = null;

        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("select * from room where id=? ")) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                rs.next();
               ClassRoom classRoom = ClassRoom.valueOf(rs.getString("classOfAp"));
                room = new Room(rs.getInt("id"), rs.getInt("numOfSeats"),
                        classRoom, rs.getInt("price"));
            }
            log.info("Room with room_id: {} readed", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read room with room_id: {} readed", id, e);
        }
        return room;
    }
}
