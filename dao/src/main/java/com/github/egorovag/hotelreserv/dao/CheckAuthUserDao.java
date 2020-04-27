package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.api.IcheckAuthUserDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
//import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.api.Role;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckAuthUserDao implements IcheckAuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(CheckAuthUserDao.class);
    private static volatile IcheckAuthUserDao instance;

    public static IcheckAuthUserDao getInstance() {
        IcheckAuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (IcheckAuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CheckAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String checkLoginDao(String login) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement("select login from authuser where login = ?")) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            log.info("authUser with login: {} readed", login);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read authUser with login: {}", login, e);
        }
        return null;
    }

//  +  @Override
//    public String checkLoginDao(String login) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            String loginResult = (String) session.createNativeQuery("select login from authuser where login = :login")
//                    .setParameter("login", login).getSingleResult();
//            session.getTransaction().commit();
//            log.info("authUser with login: {} readed", login);
//            return loginResult;
//        } catch (NoResultException e) {
//            log.error("Fail to read authUser with login: {}", login, e);
//            return null;
//        }
//    }

    @Override
    public String readPasswordByLoginDao(String login) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement("select password from authuser where login = ?")) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
            log.info("authuser password with login: {} readed", login);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read authuser password with login: {}", login, e);
        }
        return null;
    }

//   + @Override
//    public String readPasswordByLoginDao(String login) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            String passwordResult = (String) session.createNativeQuery("select password from authuser where login = :login")
//                    .setParameter("login", login).getSingleResult();
//            session.getTransaction().commit();
//            log.info("authuser password ={} with login: {} readed", passwordResult, login);
//            return passwordResult;
//        } catch (NoResultException e) {
//            log.error("Fail to read authuser password with login: {}", login, e);
//            return null;
//        }
//    }

    @Override
    public AuthUser saveUserDao(String login, String password, Role role) {
        int id = 0;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into authuser(login,password,role) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, String.valueOf(role));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            log.info("authuser with login:{}, password:{}, role{} saved", login, password, role);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to save authuser with login:{}, password:{}, role{}", login, password, role, e);
        }
        return new AuthUser(id, login, password, role);
    }

//   + @Override
//    public AuthUser saveUserDao(String login, String password, Role role) {
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        int id = (int) session.save(new AuthUser(login, password, role));
//        session.getTransaction().commit();
//        session.close();
//        return new AuthUser(id, login, password, role);
//    }

    @Override
    public AuthUser readUserByLoginDao(String login) {
        AuthUser authUser = null;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select * from authuser where login=? ")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Role role = Role.valueOf(rs.getString("role"));
                authUser = new AuthUser(rs.getInt("id"), rs.getString("login"),
                        rs.getString("password"), role);
            }
            log.info("authuser with login: {} readed", login);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read authuser with login: {}", login, e);
        }
        return authUser;
    }

//   + @Override
//    public AuthUser readUserByLoginDao(String login) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUseRes = (AuthUser) session.createQuery("select A from AuthUser A  where login = :login")
//                    .setParameter("login", login).getSingleResult();
//            session.getTransaction().commit();
//            log.info("authuser with login: {} readed", login);
//            return authUseRes;
//        } catch (HibernateError e) {
//            log.error("Fail to read authuser with login: {}", login, e);
//        }
//        return null;
//    }

    @Override
    public Client readClientByLoginDao(String login) {
        Client client = null;
        String userLogin = null;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select * from authuser join client on authuser.id = client.user_id where login=? ")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getString("firstName"), rs.getString("secondName"),
                        rs.getString("email"), rs.getString("phone"), rs.getInt("user_id"));
            }
            log.info("Client with login:{} readed", login);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read client with login:{}", login, e);
        }
        return client;
    }


//    @Override
//    public Client readClientByLoginDao(String login) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            Client client = session.createQuery("select C from Client C join AuthUser on Client.userId=AuthUser.id where AuthUser.login = :login", Client.class)
//                    .setParameter("login", login).getSingleResult();
//            session.getTransaction().commit();
//            log.info("Client with login:{} readed", login);
//            return client;
//        } catch (HibernateError e) {
//            log.error("Fail to read client with login:{}", login, e);
//        }
//        return null;
//    }

    @Override
    public List<AuthUserWithClient> readListClientDao() {
        List<AuthUserWithClient> listAU = new ArrayList<>();
        try (Connection connection = MysqlDataBase.connect();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("select authuser.id, login, password, firstname, secondname, email, phone" +
                    " from authuser join client on authuser.id = client.user_id ")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String firstname = rs.getString("firstname");
                    String secondname = rs.getString("secondname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    AuthUserWithClient authUserList = new AuthUserWithClient(id, login, password, firstname, secondname, email, phone);
                    listAU.add(authUserList);
                }
            }
            log.info("List<AuthUser> readed: {}", listAU);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read List<AuthUser>", e);
        }
        return listAU;
    }

    @Override
    public boolean deleteUserByLoginDao(String login) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from authuser where login =?")) {
            statement.setString(1, login);
            statement.executeUpdate();
            log.info("authuser with login:{} deleted", login);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete authuser with login:{}", login);
        }
        return true;
    }


//  +  @Override
//    public boolean deleteUserByLoginDao(String login) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser = session.createQuery("select A from AuthUser A where login = :login", AuthUser.class)
//                    .setParameter("login", login).getSingleResult();
//            session.delete(authUser);
//            session.getTransaction().commit();
//            log.info("authuser with login:{} deleted", login);
//            return true;
//        } catch (HibernateError e) {
//            log.error("Fail to delet authuser with login:{}", login);
//        }
//        return false;
//    }

    @Override
    public boolean deleteUserByIdDao(int id) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from authuser where id =?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            log.info("authuser with id:{} deleted", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete authuser with id:{}", id);
        }
        return true;
    }


// +   @Override
//    public boolean deleteUserByIdDao(int id) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser = session.createQuery("select A from AuthUser A where id = :id", AuthUser.class)
//                    .setParameter("id", id).getSingleResult();
//            session.delete(authUser);
//            session.getTransaction().commit();
//            log.info("authuser with id:{} deleted", id);
//            return true;
//        } catch (HibernateError e) {
//            log.error("Fail to delet authuser with id:{}", id);
//        }
//        return false;
//    }
}
