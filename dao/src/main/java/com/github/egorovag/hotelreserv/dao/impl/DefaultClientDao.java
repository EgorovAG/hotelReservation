package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
//import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DefaultClientDao implements ClientDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultClientDao.class);
    public static volatile com.github.egorovag.hotelreserv.dao.ClientDao instance;

    public static ClientDao getInstance() {
        ClientDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ClientDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultClientDao();
                }
            }
        }
        return localInstance;
    }


//    @Override
//    public boolean saveClientDao(Client client) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("insert into client(firstName,secondName,email,phone,user_id) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, client.getFirstName());
//            statement.setString(2, client.getSecondName());
//            statement.setString(3, client.getEmail());
//            statement.setString(4, client.getPhone());
//            statement.setInt(5, client.getUserId());
//            statement.executeUpdate();
//            log.info("Client : {} saved", client);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to save client: {}", client, e);
//        }
//        return true;
//    }

    @Override
    public boolean saveClientDao(Client client) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
            log.info("Client : {} saved", client);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to save client: {}", client, e);
            return false;
        }
    }


//    @Override
//    public boolean deleteClientByClientIdDao(int id) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("delete from client where user_id =?")) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            log.info("Client with client_id: {} deleted", id);
//            return true;
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to delete client with client_id: {}", id, e);
//            return false;
//        }
//    }

//    @Override
//    public boolean deleteClientByClientIdDao(int id) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            Client client = session.createQuery("select C from Client C where userId = :id", Client.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//            session.delete(client);
//            session.getTransaction().commit();
//            log.info("Client with client_id: {} deleted", id);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to delete client with client_id: {}", id, e);
//            return false;
//        }
//    }

    @Override //oneToOne
    public Integer saveAuthUserAndClientDao(AuthUser authUser, Client client) {
        authUser.setClient(client);
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Integer id = (Integer) session.save(authUser);
            session.getTransaction().commit();
            log.info("AuthUser: {} and Client : {} saved", authUser, client);
            return id;
        } catch (HibernateException e) {
            log.error("Fail to save AuthUser: {} and Client : {} ", authUser, client , e);
            return null;
        }
    }

    @Override //oneToOne
    public boolean deleteAuthUserAndClientByUserIdDao(Integer userId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.get(AuthUser.class,userId);
            session.delete(authUser);
            session.getTransaction().commit();
            log.info("AuthUser with : {} userId and Client deleted", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete AuthUser with : {} userId and Client ", userId, e);
            return false;
        }
    }


}


// ЛИШНЕЕ!!!!!
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

