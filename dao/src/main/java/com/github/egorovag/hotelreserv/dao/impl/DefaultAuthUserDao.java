package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.Role;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private static volatile AuthUserDao instance;

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultAuthUserDao();
                }
            }
        }
        return localInstance;
    }

//    @Override
//    public String checkLoginDao(String login) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement("select login from authuser where login = ?")) {
//            statement.setString(1, login);
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString(1);
//                }
//            }
//            log.info("authUser with login: {} readed", login);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read authUser with login: {}", login, e);
//        }
//        return null;
//    }

    @Override
    public String checkLoginDao(String login) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String loginResult = (String) session.createNativeQuery("select login from authUser where login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("authUser with login: {} readed", login);
            return loginResult;
        } catch (NoResultException e) {
            log.error("Fail to read authUser with login: {}", login, e);
        }
        return null;
    }


//    @Override
//    public AuthUser saveUserDao(String login, String password, Role role) {
//        int id = 0;
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("insert into authuser(login,password,role) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, login);
//            statement.setString(2, password);
//            statement.setString(3, String.valueOf(role));
//            statement.executeUpdate();
//            ResultSet rs = statement.getGeneratedKeys();
//            if (rs.next()) {
//                id = rs.getInt(1);
//            }
//            log.info("authuser with login:{}, password:{}, role{} saved", login, password, role);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to save authuser with login:{}, password:{}, role{}", login, password, role, e);
//        }
//        return new AuthUser(id, login, password, role);
//    }

    @Override
    public AuthUser saveUserDao(String login, String password, Role role) {
        int id;
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            id = (int) session.save(new AuthUser(login, password, role));
            session.getTransaction().commit();
            log.info("authuser with login:{}, password:{}, role{} saved", login, password, role);
            return new AuthUser(id, login, password, role);
        } catch (HibernateException e) {
            log.error("Fail to save authuser with login:{}, password:{}, role{}", login, password, role, e);
            return null;
        }
    }

//    @Override
//    public AuthUser readUserByLoginDao(String login) {
//        AuthUser authUser = null;
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("select * from authuser where login=? ")) {
//            statement.setString(1, login);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                Role role = Role.valueOf(rs.getString("role"));
//                authUser = new AuthUser(rs.getInt("id"), rs.getString("login"),
//                        rs.getString("password"), role);
//            }
//            log.info("authuser with login: {} readed", login);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read authuser with login: {}", login, e);
//        }
//        return authUser;
//    }

    @Override
    public AuthUser readUserByLoginDao(String login) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUserRes = (AuthUser) session.createQuery("select A from AuthUser A  where login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("authuser with login: {} readed", login);
            return authUserRes;
        } catch (HibernateError e) {
            log.error("Fail to read authuser with login: {}", login, e);
        }
        return null;
    }

//    @Override
//    public Client readClientByLoginDao(String login) {
//        Client client = null;
//        String userLogin = null;
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("select * from authuser join client on authuser.id = client.user_id where login=? ")) {
//            statement.setString(1, login);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                client = new Client(rs.getString("firstName"), rs.getString("secondName"),
//                        rs.getString("email"), rs.getString("phone"), rs.getInt("user_id"));
//            }
//            log.info("Client with login:{} readed", login);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read client with login:{}", login, e);
//        }
//        return client;
//    }


    @Override
    public Client readClientByLoginDao(String login) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUserRes = session.createQuery("select A from AuthUser A where login = :login", AuthUser.class)
                    .setParameter("login", login)
                    .getSingleResult();
            Client client = session.createQuery("select C from Client C where C.userId = :id", Client.class)
                    .setParameter("id", authUserRes.getId())
                    .getSingleResult();
            session.getTransaction().commit();
            log.info("Client with login:{} readed", login);
            return client;
        } catch (HibernateError e) {
            log.error("Fail to read client with login:{}", login, e);
            return null;
        }
    }

//    @Override
//    public List<AuthUserWithClient> readListClientDao() {
//        List<AuthUserWithClient> listAU = new ArrayList<>();
//        try (Connection connection = MysqlDataBase.connect();
//             Statement statement = connection.createStatement()) {
//            try (ResultSet rs = statement.executeQuery("select a.id, a.login, a.password, c.firstname, c.secondname, c.email, c.phone" +
//                    " from authuser a join client c on a.id = c.user_id ")) {
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String login = rs.getString("login");
//                    String password = rs.getString("password");
//                    String firstname = rs.getString("firstname");
//                    String secondname = rs.getString("secondname");
//                    String email = rs.getString("email");
//                    String phone = rs.getString("phone");
//                    AuthUserWithClient authUserList = new AuthUserWithClient(id, login, password, firstname, secondname, email, phone);
//                    listAU.add(authUserList);
//                }
//            }
//            log.info("List<AuthUserWithClient> readed: {}", listAU);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read List<AuthUserWithClient>", e);
//        }
//        return listAU;
//    }

    @Override
    public List<AuthUserWithClient> readListClientDao() {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            List<AuthUserWithClient> listAU = session.createNativeQuery("select a.id,a.login,a.password,c.firstName,c.secondName,c.email,c.phone from authuser a join client c on a.id = c.user_id")
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("login", StandardBasicTypes.STRING)
                    .addScalar("password", StandardBasicTypes.STRING)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("phone", StandardBasicTypes.STRING)
                    .setResultTransformer(Transformers.aliasToBean(AuthUserWithClient.class))
                    .list();
            session.getTransaction().commit();
            log.info("List<AuthUserWithClient> readed: {}", listAU);
            return listAU;
        } catch (HibernateException e) {
            log.error("Fail to read List<AuthUserWithClient>", e);
            return null;
        }
    }


//    @Override
//    public boolean deleteUserByLoginDao(String login) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("delete from authuser where login =?")) {
//            statement.setString(1, login);
//            statement.executeUpdate();
//            log.info("authuser with login:{} deleted", login);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to delete authuser with login:{}", login, e);
//        }
//        return true;
//    }


    @Override
    public boolean deleteUserByLoginDao(String login) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.createQuery("select A from AuthUser A where login = :login", AuthUser.class)
                    .setParameter("login", login)
                    .getSingleResult();
            session.delete(authUser);
            session.getTransaction().commit();
            log.info("authuser with login:{} deleted", login);
            return true;
        } catch (HibernateError e) {
            log.error("Fail to delete authuser with login:{}", login, e);
        }
        return false;
    }

//    @Override
//    public boolean deleteUserByIdDao(int id) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement
//                     ("delete from authuser where id =?")) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            log.info("authuser with id:{} deleted", id);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to delete authuser with id:{}", id, e);
//        }
//        return true;
//    }


    @Override
    public boolean deleteUserByIdDao(int id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            AuthUser authUser = session.createQuery("select A from AuthUser A where id = :id", AuthUser.class)
                    .setParameter("id", id).getSingleResult();
            session.delete(authUser);
            session.getTransaction().commit();
            log.info("authuser with id:{} deleted", id);
            return true;
        } catch (HibernateError e) {
            log.error("Fail to delete authuser with id:{}", id, e);
        }
        return false;
    }
}


// лишний метод !!!!!!!

//    @Override
//    public String readPasswordByLoginDao(String login) {
//        try (Connection connection = MysqlDataBase.connect();
//             PreparedStatement statement = connection.prepareStatement("select password from authuser where login = ?")) {
//            statement.setString(1, login);
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("password");
//                }
//            }
//            log.info("authuser password with login: {} readed", login);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("Fail to read authuser password with login: {}", login, e);
//        }
//        return null;
//    }

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
