package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private static OrderDao orderDao;

    @InjectMocks
    private static OrderService orderService;

    Room room = new Room(1, 1, ClassRoom.ECONOM);
    Client client = new Client(5, "Alex", "Alexandrov", "tut@tut.by", "55555", 4);
    OrderClient orderClient = new OrderClient(10, "2020-05-10", "2020-05-12", 1, 5,
            Condition.CONSIDERATION, room, client);

    @BeforeAll
    static void createInstance() {
        orderService = DefaultOrderService.getInstance();
    }

    @Test
    void testSaveOrder() {
        when(orderDao.saveOrderDao(orderClient)).thenReturn(orderClient);
        OrderClient orderResult = orderService.saveOrder(orderClient);
        Assertions.assertEquals(orderClient, orderResult);
    }

    @Test
    void testReadOrderList() {
        List<OrderForAdmin> orderForAdmins = new ArrayList<>();
        when(orderDao.readOrderListDao()).thenReturn(orderForAdmins);
        List<OrderForAdmin> orderForAdminsRes = orderService.readOrderList();
        Assertions.assertEquals(orderForAdmins, orderForAdminsRes);
    }

    @Test
    void testUpdateOrderList() {
        List<OrderForAdmin> orderForAdmins = new ArrayList<>();
        when(orderDao.updateOrderListDao(orderClient.getOrderId(), orderClient.getCondition())).thenReturn(true);
        boolean res = orderService.updateOrderList(orderClient.getOrderId(), orderClient.getCondition());
        Assertions.assertTrue(res);
    }

    @Test
    void testReadPriceForRoomByOrderId() {
        when(orderDao.readPriceForRoomByOrderIdDao(orderClient.getOrderId())).thenReturn(100);
        int res = orderService.readPriceForRoomByOrderId(orderClient.getOrderId());
        Assertions.assertEquals(100, res);
    }

    @Test
    void testReadOrderForClientByClientId() {
        List<OrderForClient> orderForClients = new ArrayList<>();
        when(orderDao.readOrderForClientByClientIdDao(orderClient.getClientId())).thenReturn(orderForClients);
        List<OrderForClient> orderForClientsRes = orderService.readOrderForClientByClientId(orderClient.getClientId());
        Assertions.assertEquals(orderForClients, orderForClientsRes);
    }

    @Test
    void testReadOrderClientListByClientId() {
        List<OrderClient> orderClients = new ArrayList<>();
        when(orderDao.readOrderClientListByClientIdDao(orderClient.getClientId())).thenReturn(orderClients);
        List<OrderClient> orderClientsRes = orderService.readOrderClientListByClientId(orderClient.getClientId());
        Assertions.assertEquals(orderClients, orderClientsRes);
    }

    @Test
    void testCheckIdOrderByClientOrder() {
        when(orderDao.checkIdOrderByClientOrderDao(orderClient.getOrderId())).thenReturn(orderClient.getClientId());
        boolean res = orderService.checkIdOrderByClientOrder(orderClient.getOrderId(), orderClient.getClientId());
        Assertions.assertTrue(res);
    }

    @Test
    void testDeleteOrderByOrderId() {
        when(orderDao.deleteOrderByOrderIdDao(orderClient.getOrderId())).thenReturn(true);
        boolean res = orderService.deleteOrderByOrderId(orderClient.getOrderId());
        Assertions.assertTrue(res);
    }

    @Test
    void testReadConditionByOrderId() {
        when(orderDao.readConditionByOrderIdDao(orderClient.getOrderId())).thenReturn(Condition.CONSIDERATION);
        Condition conditionRes = orderService.readConditionByOrderId(orderClient.getOrderId());
        Assertions.assertEquals(Condition.CONSIDERATION, conditionRes);
    }
}




