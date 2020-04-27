package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.api.IOrderDao;
import com.github.egorovag.hotelreserv.dao.api.IcheckAuthUserDao;
import com.github.egorovag.hotelreserv.dao.api.IclientDao;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.api.Condition;
import com.github.egorovag.hotelreserv.model.api.Role;
import org.junit.jupiter.api.*;

import java.util.List;

class OrderDaoTest {
    private IclientDao iclientDao = ClientDao.getInstance();
    private IcheckAuthUserDao icheckAuthUserDao = CheckAuthUserDao.getInstance();
    private IOrderDao iOrderDao = OrderDao.getInstance();
    private AuthUser authUser;
    private OrderClient orderWithoutId;
    private OrderClient order;
    private int clientId;

    @BeforeEach
    void saveOrder(){
        authUser =icheckAuthUserDao.saveUserDao("alex", "pass", Role.USER);
        clientId = authUser.getId();
        Client client = new Client("Alex","Alexandrov","alex@tut.by","55555", clientId);
        iclientDao.saveClientDao(client);
        orderWithoutId =new OrderClient("2020-05-10","2020-05-12", 1, Condition.CONSIDERATION);
        order = iOrderDao.saveOrderDao(orderWithoutId, clientId);
    }

    @AfterEach
    void deleteOrder(){
        iOrderDao.deleteOrderByClientIdDao(clientId);
        iclientDao.deleteClientByClientIdDao(clientId);
        icheckAuthUserDao.deleteUserByLoginDao(authUser.getLogin());
    }

    @Test
    void test(){
        List<OrderForClient> orderForClients = iOrderDao.readOrderForClientByClientIdDao(clientId);
        Assertions.assertTrue(orderForClients.size()==1);
    }
//    @Test
//    void test(){
//        List<OrderClient> orderClients = iOrderDao.readOrderByAuthUserIdDao(clientId);
//        Assertions.assertTrue(orderClients.size()==1);
//    }

//    @Test
//    void testSaveOrderDao() {
//        Assertions.assertEquals(orderWithoutId.getRoomId(), order.getRoomId());
//        Assertions.assertEquals(orderWithoutId.getStartDate(), order.getStartDate());
//    }
//
//    @Test
//    void testReadOrderListDao() {
//        List<OrderForAdmin> orderLists = iOrderDao.readOrderListDao();
//        Assertions.assertTrue(orderLists.size()==1);
//    }
//
//    @Test
//    void testReadOrderByAuthUserIdDao() {
//        OrderClient result = iOrderDao.readOrderByAuthUserIdDao(authUser.getId());
//        Assertions.assertEquals(orderWithoutId.getRoomId(), result.getRoomId());
//        Assertions.assertEquals(orderWithoutId.getStartDate(), result.getStartDate());
//    }
//
//    @Test
//    void testUpdateOrderListDao() {
//        int orderId=order.getId();
//        int condId = 2;
//        iOrderDao.updateOrderListDao(orderId, condId);
//        Order orderRes= iOrderDao.readOrderByAuthUserIdDao(clientId);
//        Assertions.assertEquals(condId, orderRes.getCondId());
//    }
//
//    @Test
//    void  testDeleteOrderByClient_idDao(){
//        iOrderDao.deleteOrderByClient_idDao(clientId);
//        iclientDao.deleteClientByClient_idDao(clientId);
//        boolean result = icheckAuthUserDao.deleteUserByLoginDao(authUser.getLogin());
//        Assertions.assertTrue(result);
//    }
}