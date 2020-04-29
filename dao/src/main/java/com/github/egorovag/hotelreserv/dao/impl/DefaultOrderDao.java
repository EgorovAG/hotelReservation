package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.utils.MysqlDataBase;
import com.github.egorovag.hotelreserv.dao.utils.SFUtil;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.OrderForClient;
import com.github.egorovag.hotelreserv.model.ClassRoom;
import com.github.egorovag.hotelreserv.model.Condition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao implements com.github.egorovag.hotelreserv.dao.OrderDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultOrderDao.class);


    private static volatile com.github.egorovag.hotelreserv.dao.OrderDao instance;

    public static com.github.egorovag.hotelreserv.dao.OrderDao getInstance() {
        com.github.egorovag.hotelreserv.dao.OrderDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BlackListUsersDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public OrderClient saveOrderDao(OrderClient orderWithoutId, int clientId) {
        int orderId = 0;
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into orderClient(startDate, endDate, room_id, client_id, conditions) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, orderWithoutId.getStartDate());
            statement.setString(2, orderWithoutId.getEndDate());
            statement.setInt(3, orderWithoutId.getRoomId());
            statement.setInt(4, clientId);
            statement.setString(5, String.valueOf(orderWithoutId.getCondition()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            log.info("Order with startDate: {}, endDate: {}, room_id: {}, client_id: {}, cond_id: {} saved",
                    orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to save Order with startDate: {}, endDate: {}, room_id: {}, client_id: {}, cond_id: {}",
                    orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition(), e);
        }
        return new OrderClient(orderId, orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition());
    }

//    @Override
//    public OrderClient saveOrderDao(OrderClient orderWithoutId, int clientId) {
//        OrderClient orderClient = new OrderClient(orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition());
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            int idRes = (int) session.save(orderClient);
//            session.getTransaction().commit();
//            log.info("Order with startDate: {}, endDate: {}, room_id: {}, client_id: {}, cond_id: {} saved",
//                    orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition());
//            return new OrderClient(idRes, orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition());
//        } catch ( HibernateException e) {
//            log.error("Fail to save Order with startDate: {}, endDate: {}, room_id: {}, client_id: {}, cond_id: {}",
//                    orderWithoutId.getStartDate(), orderWithoutId.getEndDate(), orderWithoutId.getRoomId(), clientId, orderWithoutId.getCondition(), e);
//            return null;
//        }
//    }


    @Override
    public List<OrderForAdmin> readOrderListDao() {
        List<OrderForAdmin> listOrder = new ArrayList<>();
        try (Connection connection = MysqlDataBase.connect();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("select oC.id, firstName, secondName, email, phone, client_id, startDate, endDate, conditions from client join orderClient oC on client.user_id = oC.client_id")) {
                while (rs.next()) {
                    int orderId = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String secondName = rs.getString(3);
                    String email = rs.getString(4);
                    String phone = rs.getString(5);
                    int clientId = rs.getInt(6);
                    String startDate = rs.getString(7);
                    String endDate = rs.getString(8);
                    Condition condition = Condition.valueOf(rs.getString(9));
                    OrderForAdmin orderList = new OrderForAdmin(orderId, firstName, secondName, email, phone, clientId, startDate, endDate, condition);
                    listOrder.add(orderList);
                }
            }
            log.info("List<OrderForAdmin> readed");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to read List<OrderForAdmin>", e);
        }
        return listOrder;
    }

//    @Override
//    public List<OrderForAdmin> readOrderListDao() {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction().commit();
//            List<OrderForAdmin> orderForAdmins = session.createNativeQuery("select oC.id, firstName, secondName, email, " +
//                    "phone, client_id, startDate, endDate, conditions from client join orderClient oC on client.user_id = oC.client_id")
//                    .addScalar("id", StandardBasicTypes.INTEGER)
//                    .addScalar("firstName", StandardBasicTypes.STRING)
//                    .addScalar("secondName", StandardBasicTypes.STRING)
//                    .addScalar("email", StandardBasicTypes.STRING)
//                    .addScalar("phone", StandardBasicTypes.STRING)
//                    .addScalar("client_id", StandardBasicTypes.INTEGER)
//                    .addScalar("startDate", StandardBasicTypes.STRING)
//                    .addScalar("endDate", StandardBasicTypes.STRING)
//                    .addScalar("conditions", StandardBasicTypes.STRING)
//                    .setResultTransformer(Transformers.aliasToBean(OrderForAdmin.class))
//                    .list();
//            session.getTransaction().commit();
//            log.info("List<OrderForAdmin> readed");
//            return orderForAdmins;
//        } catch (HibernateException e) {
//            log.error("Fail to read List<OrderForAdmin>", e);
//        }
//        return null;
//    }


    @Override
    public List<OrderClient> readOrderByAuthUserIdDao(int id) {
        List<OrderClient> orderList = new ArrayList<>();
        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from orderClient where client_id =?")) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("id");
                    String startDate = rs.getString("startDate");
                    String endDate = rs.getString("endDate");
                    int roomId = rs.getInt("room_id");
                    int clientId = rs.getInt("client_id");
                    String condition = rs.getString("conditions");
                    OrderClient order = new OrderClient(orderId, startDate, endDate, roomId, clientId, Condition.valueOf(condition));
                    orderList.add(order);
                }
            }
            log.info("List<OrderClient> with id: {} readed", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail read List<OrderClient> with id: {}", id, e);
        }
        return orderList;
    }

//    @Override
//    public List<OrderClient> readOrderByAuthUserIdDao(int id) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            List<OrderClient> orderClients = session.createQuery("select OC FROM OrderClient WHERE userId = :userId ")
//                    .setParameter("userId", id)
//                    .getResultList();
//            session.getTransaction().commit();
//            log.info("List<OrderClient> with id: {} readed", id);
//            return orderClients;
//        } catch (HibernateException e) {
//            log.error("Fail read List<OrderClient> with id: {}", id, e);
//            return null;
//        }
//    }

    @Override
    public List<OrderForClient> readOrderForClientByClientIdDao(int id) {
        List<OrderForClient> orderForClients = new ArrayList<>();
        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement("select oc.id, startDate, endDate,numOfSeats, classOfAp, price, conditions from orderclient as oc join room r on oc.room_id = r.id where oc.client_id=?")) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt(1);
                    String startDate = rs.getString(2);
                    String endDate = rs.getString(3);
                    int numOfSeats = rs.getInt(4);
                    ClassRoom classRoom = ClassRoom.valueOf(rs.getString(5));
                    int price = rs.getInt(6);
                    Condition condition = Condition.valueOf(rs.getString(7));
                    OrderForClient orderForClient = new OrderForClient(orderId, startDate, endDate, numOfSeats, classRoom, price, condition);
                    orderForClients.add(orderForClient);
                }
            }
            log.info("OrderForClient with id: {} readed", id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail read OrderForClient with id: {}", id, e);
        }
        return orderForClients;
    }

//    @Override
//    public List<OrderForClient> readOrderForClientByClientIdDao(int id) {
//          try (Session session = SFUtil.getSession()) {
//            session.beginTransaction().commit();
//            List<OrderForClient> orderForClients = session.createNativeQuery("select oc.id, startDate, endDate,numOfSeats, classOfAp, price, conditions from orderclient as oc join room r on oc.room_id = r.id where oc.client_id= :id")
//                    .setParameter("id", id)
//                    .addScalar("id", StandardBasicTypes.INTEGER)
//                    .addScalar("startDate", StandardBasicTypes.STRING)
//                    .addScalar("endDate", StandardBasicTypes.STRING)
//                    .addScalar("numOfSeats", StandardBasicTypes.INTEGER)
//                    .addScalar("classOfAp", StandardBasicTypes.INTEGER)
//                    .addScalar("price", StandardBasicTypes.INTEGER)
//                    .addScalar("conditions", StandardBasicTypes.STRING)
//                    .setResultTransformer(Transformers.aliasToBean(OrderForAdmin.class))
//                    .list();
//            session.getTransaction().commit();
//              log.info("OrderForClient with id: {} readed", id);
//            return orderForClients;
//        } catch (HibernateException e) {
//              log.error("Fail read OrderForClient with id: {}", id, e);
//              return null;
//          }
//    }


    @Override
    public boolean updateOrderListDao(int orderId, Condition condition) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("update orderclient set conditions=? where id=?")) {
            statement.setString(1, String.valueOf(condition));
            statement.setInt(2, orderId);
            statement.executeUpdate();
            log.info("cond_id: {} orderclient with order_id: {} updated", orderId, condition);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to update cond_id: {} orderclient with order_id: {}", orderId, condition, e);
        }
        return true;
    }

//    @Override
//    public boolean updateOrderListDao(int orderId, Condition condition) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            session.createNativeQuery("update orderclient set conditions = :condition where id =:id")
//                    .setParameter("condition", condition)
//                    .setParameter("id", orderId)
//                    .executeUpdate();
//            log.info("cond_id: {} orderclient with order_id: {} updated", orderId, condition);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to update cond_id: {} orderclient with order_id: {}", orderId, condition, e);
//            return false;
//        }
//    }

    @Override
    public boolean deleteOrderByClientIdDao(int id) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from orderclient where client_id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            log.info("orderclient with client_id:{} deleted", id);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete orderclient with client_id:{}", id, e);
        }
        return false;
    }

//    @Override
//    public boolean deleteOrderByClientIdDao(int id) {
//        try (Session session = SFUtil.getSession()){
//            session.beginTransaction();
//            OrderClient orderClient = session.createQuery("delete OrderClient where userId = :id", OrderClient.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//            session.delete(orderClient);
//            session.beginTransaction().commit();
//            log.info("orderclient with client_id:{} deleted", id);
//            return true;
//            } catch (HibernateException e) {
//            log.error("Fail to delete orderclient with client_id:{}", id, e);
//            return false;
//        }
//    }


    @Override
    public int readPriceByOrderIdDao(int orderId) {
        int price = 0;
        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement("select price from room join orderClient oC on room.id = oC.room_id where oC.id =?")) {
                statement.setInt(1, orderId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("price");
                }
            }
            log.info("Price with orderId: {} readed", orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail read price with orderId: {}", orderId, e);
        }
        return price;
    }

//    @Override
//    public int readPriceByOrderIdDao(int orderId) {
//        int price = 0;
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            price = (int) session.createNativeQuery("select r.price  from room r join orderClient oC on r.id = oC.room_id where oC.id = :orderId")
//                    .setParameter("orderId", orderId)
//                    .getSingleResult();
//            session.beginTransaction().commit();
//            log.info("Price with orderId: {} readed", orderId);
//            return price;
//        } catch (HibernateException e) {
//            //  разобраться что возвращает price или ОШИБКУ!!!!!
//            log.error("Fail read price with orderId: {}", orderId, e);
//            return price;
//        }
//    }

    @Override
    public boolean deleteOrderByOrderIdDao(int orderId) {
        try (Connection connection = MysqlDataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from orderclient where id=?")) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
            log.info("orderclient with client_id:{} deleted", orderId);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to delete orderclient with client_id:{}", orderId, e);
        }
        return false;
    }

//    @Override
//    public boolean deleteOrderByOrderIdDao(int orderId) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            OrderClient orderClient = session.createQuery("select oC from OrderClient oC where id = :id", OrderClient.class)
//                    .setParameter("id", orderId)
//                    .getSingleResult();
//            session.delete(orderClient);
//            session.beginTransaction().commit();
//            log.info("orderclient with client_id:{} deleted", orderId);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to delete orderclient with client_id:{}", orderId, e);
//            return false;
//        }
//    }

    @Override
    public int checkIdOrderByClientOrderDao(int orderId) {
        int id = 0;
        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement("select client_id from orderclient where id=?")) {
                statement.setInt(1, orderId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("client_id");
                }
            }
            log.info("ClientId with orderId:{} readed ", orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail read ClientId with orderId:{} ", orderId, e);
        }
        return id;
    }

//    @Override
//    public int checkIdOrderByClientOrderDao(int orderId) {
//        int id = 0;
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            id = (int) session.createQuery("select OrderClient.userId from OrderClient where id = :id")
//                    .setParameter("id", orderId)
//                    .getSingleResult();
//            session.beginTransaction().commit();
//            log.info("ClientId with orderId:{} readed ", orderId);
//            return id;
//        } catch (HibernateException e) {
//            log.error("Fail read ClientId with orderId:{} ", orderId, e);
//            return id;
//        }
//    }

    @Override
    public Condition readConditionByOrderIdDao(int orderId) {
        try (Connection connection = MysqlDataBase.connect()) {
            try (PreparedStatement statement = connection.prepareStatement("select conditions from orderclient where id =?")) {
                statement.setInt(1, orderId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return Condition.valueOf(rs.getString("conditions"));
                }
            }
            log.info("Condition with orderId: {} readed", orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Fail read condition with orderId: {}", orderId, e);
        }
        return null;
    }

//    @Override
//    public Condition readConditionByOrderIdDao(int orderId) {
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            String condition = (String) session.createQuery("select condition from OrderClient where id = :id")
//                    .setParameter("id", orderId)
//                    .getSingleResult();
//            session.beginTransaction().commit();
//            log.info("Condition with orderId: {} readed", orderId);
//            return Condition.valueOf(condition);
//        } catch (HibernateException e) {
//            log.error("Fail read condition with orderId: {}", orderId, e);
//            return null;
//        }
//    }
}
