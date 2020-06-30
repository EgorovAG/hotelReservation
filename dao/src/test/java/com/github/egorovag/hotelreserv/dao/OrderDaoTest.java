package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class OrderDaoTest {

    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    OrderDao orderDao;

    @Autowired
    SessionFactory sessionFactory;

    private AuthUser authUser;
    private OrderClient orderClient;
    private Client client;
    private Room room;

    @BeforeEach
    void saveAuthUserAndClientAndOrder() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
        room = new Room(1, 1, ClassRoom.ECONOM);
        client = new Client(authUser.getClient().getId(), "Alex", "Alexandrov", "alex@tut.by", "55555");
        orderClient = new OrderClient(null, LocalDate.of(2020, 10, 05), LocalDate.of(2020, 10, 8), room.getId(), client.getId(),
                Condition.CONSIDERATION);
        orderClient = orderDao.saveOrderDao(orderClient, room, client);
    }

    @Test
    void testSaveOrderDao() {
        Assertions.assertNotNull(orderClient);
    }

    @Test
    void testReadOrderListDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        Assertions.assertEquals(1, orderLists.size());
    }

    @Test
    void testReadOrderForClientByClientIdDao() {
        List<OrderForClientDTO> orderForClients = orderDao.readOrderForClientDTOByClientIdDao(authUser.getClient().getId());
        Assertions.assertEquals(1, orderForClients.size());
    }

    @Test
    void testReadOrderClientListByClientIdDao() {
        List<OrderClient> orderClients = orderDao.readOrderClientListByClientIdDao(authUser.getClient().getId());
        Assertions.assertEquals(1, orderClients.size());
    }

    @Test
    void testReadOrderClientListDaoDao() {
        List<OrderClient> orderClients = orderDao.readOrderClientListDao();
        Assertions.assertEquals(1, orderClients.size());
    }

    @Test
    void testUpdateOrderListDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        orderDao.updateOrderListDao(orderLists.iterator().next().getId(), Condition.APPROVED);
        sessionFactory.getCurrentSession().flush();
        List<OrderForClientDTO> orderForClients = orderDao.readOrderForClientDTOByClientIdDao(authUser.getClient().getId());
        Assertions.assertEquals(Condition.APPROVED, orderForClients.iterator().next().getCondition());
    }

    @Test
    void testReadPriceForRoomByOrderIdDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        int price = orderDao.readPriceForRoomByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(100, price);
    }

    @Test
    void testDeleteOrderByOrderIdDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        boolean res = orderDao.deleteOrderByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertTrue(res);
    }

    @Test
    void testCheckIdOrderByClientOrderDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        int res = orderDao.checkIdOrderByClientOrderDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(authUser.getClient().getId(), res);
    }

    @Test
    void testReadConditionByOrderIdDao() {
        List<OrderForAdminDTO> orderLists = orderDao.readOrderListForAdminDTODao();
        Condition condition = orderDao.readConditionByOrderIdDao(orderLists.iterator().next().getId());
        Assertions.assertEquals(Condition.CONSIDERATION, condition);
    }
}