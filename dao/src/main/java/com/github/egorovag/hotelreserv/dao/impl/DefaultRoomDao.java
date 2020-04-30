package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.Condition;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.ClassRoom;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultRoomDao implements RoomDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultRoomDao.class);
    private static volatile RoomDao instance;

    public static RoomDao getInstance() {
        RoomDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRoomDao() ;
                }
            }
        }
        return localInstance;
    }

//    @Override
//    public Integer readRoomIdDao(int numOfSeats, String classOfApp) {
//        int id = 0;
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement("select id from room where numOfSeats =? and classOfAp =?")) {
//            statement.setInt(1, numOfSeats);
//            statement.setString(2,classOfApp);
//            try (ResultSet rs = statement.executeQuery()) {
//                if(rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//            log.info("Room_id with numOfSeats: {} and classOfApp: {} readed", numOfSeats , classOfApp);
//            return id;
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read room_id with numOfSeats: {} and classOfApp: {} ", numOfSeats , classOfApp, e);
//            return id;
//        }
//    }

    @Override
    public int readRoomIdDao(int numOfSeats, String classOfApp) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Room room = session.createQuery("SELECT r from Room r where numOfSeats = : numOfSeats and  classOfAp = :classOfApp", Room.class)
                    .setParameter("numOfSeats", numOfSeats)
                    .setParameter("classOfApp", ClassRoom.valueOf(classOfApp))
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Room_id with numOfSeats: {} and classOfApp: {} readed", numOfSeats , classOfApp);
            return room.getId();
        } catch ( HibernateException e) {
            log.error("Fail to read room_id with numOfSeats: {} and classOfApp: {} ", numOfSeats , classOfApp, e);
            return 0;
        }
    }


//    @Override
//    public Room readRoomByIdDao(int id) {
//        Room room = null;
//        try (Connection connection = MysqlDataBase.connect()) {
//            try (PreparedStatement statement = connection.prepareStatement
//                    ("select * from room where id=? ")) {
//                statement.setInt(1, id);
//                ResultSet rs = statement.executeQuery();
//                rs.next();
//               ClassRoom classRoom = ClassRoom.valueOf(rs.getString("classOfAp"));
//                room = new Room(rs.getInt("id"), rs.getInt("numOfSeats"),
//                        classRoom, rs.getInt("price"));
//            }
//            log.info("Room with room_id: {} readed", id);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read room with room_id: {} readed", id, e);
//        }
//        return room;
//    }

    @Override
    public Room readRoomByIdDao(int id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Room room = session.createQuery("select r from Room r where id = :id ", Room.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Room with room_id: {} readed", id);
            return room;
        } catch ( HibernateException e) {
            log.error("Fail to read room with room_id: {} readed", id, e);
            return null;
        }
    }
}
