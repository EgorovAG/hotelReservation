package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.api.IblackListUsersDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.model.BlackListUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class BlackListUsersDao implements IblackListUsersDao {
    private static final Logger log = LoggerFactory.getLogger(BlackListUsersDao.class);
    private static volatile IblackListUsersDao instance;

    public static IblackListUsersDao getInstance() {
        IblackListUsersDao localInstance = instance;
        if (localInstance == null) {
            synchronized (IblackListUsersDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BlackListUsersDao();
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
            log.info("List<AuthUser> readed:{}", listBL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read List<AuthUser>", e);
        }
        return listBL;
    }

    @Override
    public boolean deleteBlackListUserByIdDao(int id) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from blacklist where user_id =?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            log.info("user with id:{} deleted from blackList", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete user from blackList with id:{}", id);
        }
        return true;
    }


    @Override
    public boolean saveBlackListUserDao(int id) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into blacklist(user_id, date_block) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            log.info("Client with id:{} saved in blackList", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to save client:{} in blackList", id, e);
        }
        return true;
    }

    @Override
    public boolean checkBlackUserByIdDao(int id) {
        boolean b=true;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select count(*) as count from blacklist where user_id=?")) {
            statement.setInt(1, id);
            ResultSet rs= statement.executeQuery();
            if(rs.next()){
                if (rs.getInt(1)!=0){
                    return b = true;
                } else {
                    return b = false;
                }
            }
            log.info("Client with id:{} saved in blackList", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to save client:{} in blackList", id, e);
        }
        return b;
    }
}