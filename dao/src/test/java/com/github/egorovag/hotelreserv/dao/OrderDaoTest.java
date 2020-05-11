package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.*;

import java.util.List;

class OrderDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private AuthUser authUser;
    private OrderClient orderClient;
    private Integer clientId;
    private Client client;
    private Room room;
    private List<Service> serviceList;


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
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        boolean res = orderDao.saveOrderDao(orderClient);
        Assertions.assertTrue(res);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

    @Test
    void testReadOrderListDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        Assertions.assertEquals(1, orderLists.size());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }


    @Test
    void testReadOrderForClientByClientIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(authUserRes.getClient().getId());
        Assertions.assertEquals(1, orderForClients.size());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

    @Test
    void testUpdateOrderListDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        orderDao.updateOrderListDao(orderLists.iterator().next().getId(), Condition.APPROVED);
        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(authUserRes.getClient().getId());
        Assertions.assertEquals(Condition.APPROVED, orderForClients.iterator().next().getCondition());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

//    @Test
//    void  testDeleteOrderByClientIdDao(){
//        boolean res = orderDao.deleteOrderByClientIdDao(clientId);
//        Assertions.assertTrue(res);
//        orderDao.saveOrderDao(orderWithoutId, clientId);
//    }

    @Test
    void testReadPriceForRoomByOrderIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        int price = orderDao.readPriceForRoomByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(100, price);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());


    }

    @Test
    void testDeleteOrderByOrderIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        boolean res = orderDao.deleteOrderByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertTrue(res);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

    @Test
    void testCheckIdOrderByClientOrderDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        int res = orderDao.checkIdOrderByClientOrderDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(authUserRes.getClient().getId(),res);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

    @Test
    void testReadConditionByOrderIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Room room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderDao.saveOrderDao(orderClient);
        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
        Condition condition = orderDao.readConditionByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(Condition.CONSIDERATION,condition);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }
}



//@Test
//    void testReadOrderByAuthUserIdDao() {
//        List<OrderClient> orderClients = orderDao.readOrderByAuthUserIdDao(authUser.getId());
//        Assertions.assertEquals(1,orderClients.size());
//    }