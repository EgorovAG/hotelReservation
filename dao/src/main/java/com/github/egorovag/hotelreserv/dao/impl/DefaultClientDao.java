package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
//import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DefaultClientDao implements com.github.egorovag.hotelreserv.dao.ClientDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultClientDao.class);
    public static volatile com.github.egorovag.hotelreserv.dao.ClientDao instance;

    public static com.github.egorovag.hotelreserv.dao.ClientDao getInstance() {
        com.github.egorovag.hotelreserv.dao.ClientDao localInstance = instance;
        if (localInstance == null) {
            synchronized (com.github.egorovag.hotelreserv.dao.ClientDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultClientDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean saveClientDao(Client client) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into client(firstName,secondName,email,phone,user_id) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getSecondName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPhone());
            statement.setInt(5, client.getUserId());
            statement.executeUpdate();
            log.info("Client : {} saved", client);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to save client: {}", client, e);
        }
        return true;
    }

//  +  @Override
//    public boolean saveClientDao(Client client) {
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        session.saveOrUpdate(client);
//        session.getTransaction().commit();
//        session.close();
//        return true;
//    }

    @Override
    public boolean deleteClientByClientIdDao(int id) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from client where user_id =?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            log.info("Client with client_id: {} deleted", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete client with client_id: {}", id , e);
        }
        return true;
    }
}




//    @Override
//    public boolean deleteClientByFirstNameDao(String firstName) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("delete from client where firstName =?")) {
//            statement.setString(1, firstName);
//            statement.executeUpdate();
//            log.info("Client with firstName:{} deleted", firstName);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to delete client with client_id:{}", firstName , e);
//        }
//        return true;
//    }


//
//    @Override
//    public void updateDao() {
//    }
//
//    @Override
//    public void loadDao() {
//    }

