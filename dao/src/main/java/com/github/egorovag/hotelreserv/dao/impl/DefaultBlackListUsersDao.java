package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.BlackList;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DefaultBlackListUsersDao implements BlackListUsersDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultBlackListUsersDao.class);
    private static volatile BlackListUsersDao instance;

    public static BlackListUsersDao getInstance() {
        BlackListUsersDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BlackListUsersDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultBlackListUsersDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<BlackListUsers> readBlackListUsersListsDao() {
        List<BlackListUsers> listBL = new ArrayList<>();
        try (Connection connection = MysqlDataBase.connect();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("select blackList.id, blackList.user_id, date_block, firstName, secondName from blacklist join client c on blackList.user_id = c.user_id")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    Date dateBlock = rs.getDate("date_block");
                    String firstName = rs.getString("firstName");
                    String secondName = rs.getString("secondName");

                    BlackListUsers blackListUsers = new BlackListUsers(id, userId, firstName, secondName, dateBlock);
                    listBL.add(blackListUsers);
                }
            }
            log.info("List<BlackListUsers> readed:{}", listBL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read List<BlackListUsers>", e);
        }
        return listBL;
    }

//    @Override
//    public List<BlackListUsers> readBlackListUsersListsDao() {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            List<BlackListUsers> listBL = session.createNativeQuery("select  bl.id, bl.user_id, bl.date_block,c.firstName,c.secondName from blacklist bl join client c on bl.user_id = c.user_id")
//                    .addScalar("id", StandardBasicTypes.INTEGER)
//                    .addScalar("user_id", StandardBasicTypes.INTEGER)
//                    .addScalar("date_block", StandardBasicTypes.STRING)
//                    .addScalar("firstName", StandardBasicTypes.STRING)
//                    .addScalar("secondName", StandardBasicTypes.STRING)
//                    .setResultTransformer(Transformers.aliasToBean(BlackListUsers.class))
//
//                    .list();
//            session.getTransaction().commit();
//            log.info("List<AuthUser> readed:{}", listBL);
//            return listBL;
//        } catch (HibernateException e) {
//            log.error("Fail to read List<BlackListUsers>", e);
//            return null;
//        }
//    }

//    @Override
//    public boolean deleteBlackListUserByIdDao(int id) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("delete from blacklist where user_id =?")) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            log.info("user with id:{} deleted from blackList", id);
//            return true;
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to delete user from blackList with id:{}", id);
//            return false;
//        }
//    }

    @Override
    public boolean deleteBlackListUserByIdDao(int id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE BlackList where userId = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete user from blackList with id:{}", id);
            return false;
        }
    }


//    @Override
//    public boolean saveBlackListUserDao(int id) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("insert into blacklist(user_id, date_block) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
//            statement.setInt(1, id);
//            statement.setDate(2, Date.valueOf(LocalDate.now()));
//            statement.executeUpdate();
//            log.info("Client with id:{} saved in blackList", id);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to save client:{} in blackList", id, e);
//        }
//        return true;
//    }

    @Override
    public boolean saveBlackListUserDao(int id) {
        BlackList blackList = new BlackList(id, Date.valueOf(LocalDate.now()));
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(blackList);
            session.getTransaction().commit();
            log.info("Client with id:{} saved in blackList", id);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to save client:{} in blackList", id, e);
            return false;
        }
    }


//    @Override
//    public int checkBlackUserByIdDao(int id) {
//        Integer res = null;
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("select count(*) as count from blacklist where user_id=?")) {
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                res = rs.getInt(1);
//               return res;
//            }
//            log.info("Client with id:{} saved in blackList", id);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to save client:{} in blackList", id, e);
//        }
//        return res;
//    }

    @Override
    public int checkBlackUserByIdDao(int id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Long res = (Long) session.createQuery("select count (*) from BlackList b where b.userId = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Client with id:{} saved in blackList", id);
            return Integer.parseInt(String.valueOf(res));
        } catch (HibernateException e) {
            log.error("Fail to save client:{} in blackList", id, e);
            return 0;
        }
    }
}


//было
//@Override
//public boolean checkBlackUserByIdDao(int id) {
//    boolean b = true;
//    try (Connection connection = MysqlDataBase.connect();
//         PreparedStatement statement = connection.prepareStatement
//                 ("select count(*) as count from blacklist where user_id=?")) {
//        statement.setInt(1, id);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            if (rs.getInt(1) != 0) {
//                return b = true;
//            } else {
//                return b = false;
//            }
//        }
//        log.info("Client with id:{} saved in blackList", id);
//    } catch (SQLException | ClassNotFoundException e) {
//        e.printStackTrace();
//        log.error("Fail to save client:{} in blackList", id, e);
//    }
//    return b;
//}
