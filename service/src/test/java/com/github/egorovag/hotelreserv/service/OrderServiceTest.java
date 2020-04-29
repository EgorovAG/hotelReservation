//package com.github.egorovag.hotelreserv.service;
//
//import com.github.egorovag.hotelreserv.dao.IOrderDao;
//import com.github.egorovag.hotelreserv.model.Order;
//import com.github.egorovag.hotelreserv.model.OrderWithClient;
//import com.github.egorovag.hotelreserv.service.api.IorderService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class OrderServiceTest {
//
//    @Mock
//    private static IOrderDao iOrderDao;
//
//    @InjectMocks
//    private static IorderService iorderService;
//
//    @BeforeAll
//    static void createInstance(){
//        iorderService = OrderService.getInstance();
//    }
//
//    @Test
//    void testSaveOrder() {
//        Order orderWithoutId = new Order("2020-05-10","2020-05-12",1L,2L);
//        Order order = new Order(3L,"2020-05-10","2020-05-12",1L,2L,3L);
//        when(iOrderDao.saveOrderDao(orderWithoutId,2L)).thenReturn(order);
//        Order result = iorderService.saveOrder(orderWithoutId,2L);
//        Assertions.assertEquals(order,result);
//    }
//
//    @Test
//    void TestReadOrderListService() {
//        when(iOrderDao.readOrderListDao()).thenReturn(null);
//        List<OrderWithClient> orderLists = iOrderDao.readOrderListDao();
//        Assertions.assertNull(orderLists);
//    }
//
//    @Test
//    void TestReadOrderByAuthUserIdService() {
//        Order order = new Order(3L,"2020-05-10","2020-05-12",1L,2L,3L);
//        when(iOrderDao.readOrderByAuthUserIdDao(2L)).thenReturn(order);
//        Order result = iorderService.readOrderByAuthUserIdService(2L);
//        Assertions.assertEquals(order, result);
//    }
//
//    @Test
//    void TestUpdateOrderList() {
//        when(iOrderDao.updateOrderListDao(1L, 2L)).thenReturn(true);
//        boolean result = iorderService.updateOrderList(1L,2L);
//        Assertions.assertTrue(result);
//    }
//}