package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
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


import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private static OrderDao orderDao;

    @InjectMocks
    private static OrderService orderService;

    Room room = new Room(1,1, ClassRoom.ECONOM);
    Client client = new Client(5,"Alex","Alexandrov","tut@tut.by","55555",4);
    OrderClient orderClient = new OrderClient(10, "2020-05-10", "2020-05-12", 1,5,
            Condition.CONSIDERATION, room, client);

    @BeforeAll
    static void createInstance(){
        orderService = DefaultOrderService.getInstance();
    }

    @Test
    void testSaveOrder() {
        when(orderDao.saveOrderDao(orderClient)).thenReturn(orderClient);
        OrderClient orderResult = orderService.saveOrder(orderClient);
        Assertions.assertEquals(orderClient,orderResult);
    }

//    @Test
//    void TestReadOrderListService() {
//        when(orderDao.readOrderListDao()).thenReturn(null);
//        List<OrderWithClient> orderLists = orderDao.readOrderListDao();
//        Assertions.assertNull(orderLists);
//    }
//
//    @Test
//    void TestReadOrderByAuthUserIdService() {
//        Order order = new Order(3L,"2020-05-10","2020-05-12",1L,2L,3L);
//        when(orderDao.readOrderByAuthUserIdDao(2L)).thenReturn(order);
//        Order result = orderService.readOrderByAuthUserIdService(2L);
//        Assertions.assertEquals(order, result);
//    }
//
//    @Test
//    void TestUpdateOrderList() {
//        when(orderDao.updateOrderListDao(1L, 2L)).thenReturn(true);
//        boolean result = orderService.updateOrderList(1L,2L);
//        Assertions.assertTrue(result);
//    }
}