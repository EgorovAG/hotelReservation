package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.*;

import java.util.List;

class OrderDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private AuthUser authUser;
    private OrderClient orderWithoutId;
    private OrderClient order;
    private Integer clientId;


//    @BeforeEach
//    void saveOrder(){
//        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
//        clientId = authUser.getId();
//        Client client = new Client("Alex","Alexandrov","alex@tut.by","55555", clientId);
//        clientDao.saveClientDao(client);
//        orderWithoutId = new OrderClient("2020-05-10","2020-05-12", 1, Condition.CONSIDERATION);
//        order = orderDao.saveOrderDao(orderWithoutId, clientId);
//    }
//
//    @AfterEach
//    void deleteOrder(){
//        orderDao.deleteOrderByClientIdDao(clientId);
//        clientDao.deleteClientByClientIdDao(clientId);
//        authUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }

    @Test
    void testSaveOrderDao() {
        Assertions.assertEquals(orderWithoutId.getRoomId(), order.getRoomId());
        Assertions.assertEquals(orderWithoutId.getStartDate(), order.getStartDate());
    }

    @Test
    void testReadOrderListDao() {
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        Assertions.assertEquals(1, orderLists.size());
    }


    @Test
    void testReadOrderForClientByClientIdDao(){
        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(clientId);
        Assertions.assertEquals(1, orderForClients.size());
    }

    @Test
    void testUpdateOrderListDao() {
        int orderId = order.getOrderId();
        orderDao.updateOrderListDao(orderId, Condition.APPROVED);
        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(authUser.getId());
        OrderForClient orderClient = orderForClients.get(0);
        Assertions.assertEquals(Condition.APPROVED, orderClient.getCondition());
    }

    @Test
    void  testDeleteOrderByClientIdDao(){
        boolean res = orderDao.deleteOrderByClientIdDao(clientId);
        Assertions.assertTrue(res);
        orderDao.saveOrderDao(orderWithoutId, clientId);
    }

    @Test
    void testReadPriceByOrderIdDao(){
        int price = orderDao.readPriceByOrderIdDao(order.getOrderId());
        Assertions.assertEquals(100, price);
    }

    @Test
    void testDeleteOrderByOrderIdDao(){
        boolean res = orderDao.deleteOrderByOrderIdDao(order.getOrderId());
        Assertions.assertTrue(res);
        orderDao.saveOrderDao(orderWithoutId, clientId);
    }

    @Test
    void testCheckIdOrderByClientOrderDao(){
        int res = orderDao.checkIdOrderByClientOrderDao(order.getOrderId());
        Assertions.assertEquals(clientId,res);
    }

    @Test
    void testReadConditionByOrderIdDao(){
        Condition condition = orderDao.readConditionByOrderIdDao(order.getOrderId());
        Assertions.assertEquals(Condition.CONSIDERATION,condition);
    }
}



//@Test
//    void testReadOrderByAuthUserIdDao() {
//        List<OrderClient> orderClients = orderDao.readOrderByAuthUserIdDao(authUser.getId());
//        Assertions.assertEquals(1,orderClients.size());
//    }