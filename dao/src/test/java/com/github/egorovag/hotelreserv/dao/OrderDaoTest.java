//package com.github.egorovag.hotelreserv.dao;
//
//import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceHotelDao;
//import com.github.egorovag.hotelreserv.model.*;
//import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
//import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
//import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
//import com.github.egorovag.hotelreserv.model.enums.Condition;
//import com.github.egorovag.hotelreserv.model.enums.Role;
//import net.sf.ehcache.CacheManager;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = DaoConfig.class)
//@Transactional
//class OrderDaoTest {
//    @Autowired
//    ClientDao clientDao;
//    @Autowired
//    OrderDao orderDao;
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    private AuthUser authUser;
//    private OrderClient orderClient;
//    private Client client;
//    private Room room;
//
//
//    @BeforeEach
//    void saveAuthUserAndClientAndOrder(){
//        authUser = new AuthUser("alex", "pass", Role.USER);
//        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
//        authUser = clientDao.saveAuthUserAndClientDao(authUser,client);
//        room = new Room(1,1,ClassRoom.ECONOM);
//        client = new Client(authUser.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
//        orderClient = new OrderClient(null, LocalDate.of(2020,10,05), LocalDate.of(2020,10,8), room.getId(), client.getId(),
//                Condition.CONSIDERATION, room, client);
//        orderClient = orderDao.saveOrderDao(orderClient);
//    }
//
////    @AfterEach
////    void deleteAuthUserAndClientAndOrder(){
////        clientDao.deleteAuthUserAndClientByUserIdDao(authUser.getId());
////    }
//
//    @Test
//    void testSaveOrderDao() {
//        Assertions.assertNotNull(orderClient);
//    }
//
//    @Test
//    void testReadOrderListDao() {
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        sessionFactory.getCurrentSession().flush();
//
//        Assertions.assertEquals(1, orderLists.size());
//    }
//
//
//    @Test
//    void testReadOrderForClientByClientIdDao(){
//        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(authUser.getClient().getId());
//        Assertions.assertEquals(1, orderForClients.size());
//    }
//
//    @Test
//    void testReadOrderClientListByClientIdDao(){
//        List<OrderClient> orderClients = orderDao.readOrderClientListByClientIdDao(authUser.getClient().getId());
//        Assertions.assertEquals(1, orderClients.size());
//    }
//
//    @Test
//    void testUpdateOrderListDao() {
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        orderDao.updateOrderListDao(orderLists.iterator().next().getId(), Condition.APPROVED);
//        List<OrderForClient> orderForClients = orderDao.readOrderForClientByClientIdDao(authUser.getClient().getId());
//        Assertions.assertEquals(Condition.APPROVED, orderForClients.iterator().next().getCondition());
//    }
//
//    @Test
//    void testReadPriceForRoomByOrderIdDao(){
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        int price = orderDao.readPriceForRoomByOrderIdDao(orderLists.iterator().next().getId());
//        Assertions.assertEquals(100, price);
//    }
//
//    @Test
//    void testDeleteOrderByOrderIdDao(){
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        boolean res = orderDao.deleteOrderByOrderIdDao(orderLists.iterator().next().getId());
//        Assertions.assertTrue(res);
//    }
//
//    @Test
//    void testCheckIdOrderByClientOrderDao(){
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        int res = orderDao.checkIdOrderByClientOrderDao(orderLists.iterator().next().getId());
//        Assertions.assertEquals(authUser.getClient().getId(),res);
//    }
//
//    @Test
//    void testReadConditionByOrderIdDao(){
//        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
//        Condition condition = orderDao.readConditionByOrderIdDao(orderLists.iterator().next().getId());
//        Assertions.assertEquals(Condition.CONSIDERATION,condition);
//    }
//
////    @Test
////    void testCashL2(){
////        List<OrderForAdmin> orderLists = orderDao.readOrderListDao();
////        Condition condition = orderDao.readConditionByOrderIdDao(orderLists.get(0).getId());
////        Condition condition1 = orderDao.readConditionByOrderIdDao(orderLists.get(0).getId());
////        Condition condition2 = orderDao.readConditionByOrderIdDao(orderLists.get(0).getId());
////        Condition condition3 = orderDao.readConditionByOrderIdDao(orderLists.get(0).getId());
////
////        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
////                .getCache("com.github.egorovag.hotelreserv.model.OrderClient").getSize();
////        Assertions.assertTrue(size>0);
////    }
//}
//
//
//
//
//
//
////    @Test
////    void testSaveOrderDao() {
////        authUser = new AuthUser("alex", "pass", Role.USER);
////        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
////        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
////        Room room = new Room(1,1,ClassRoom.ECONOM);
////        client = new Client(authUserRes.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
////        Service service1 = serviceHotelDao.readServiceByTypeOfService("pool");
////        ArrayList<Service> serviceList = new ArrayList<>();
////        serviceList.add(service1);
////        OrderClient orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
////                Condition.CONSIDERATION, room, client);
////        boolean res = orderDao.saveOrderDao(orderClient, serviceList);
////        Assertions.assertTrue(res);
////        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());
////    }
//
//
////    @Test
////    void  testDeleteOrderByClientIdDao(){
////        boolean res = orderDao.deleteOrderByClientIdDao(clientId);
////        Assertions.assertTrue(res);
////        orderDao.saveOrderDao(orderWithoutId, clientId);
////    }
//
//
////@Test
////    void testReadOrderByAuthUserIdDao() {
////        List<OrderClient> orderClients = orderDao.readOrderByAuthUserIdDao(authUser.getId());
////        Assertions.assertEquals(1,orderClients.size());
////    }