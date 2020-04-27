//package com.github.egorovag.hotelreserv.service;
//
//import com.github.egorovag.hotelreserv.dao.api.IblackListUsersDao;
//import com.github.egorovag.hotelreserv.service.api.IblackListUsersService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ConditionOrderServiceTest {
//    @Mock
//    private static IblackListUsersDao iconditionOrderDao ;
//
//    @InjectMocks
//    private static IblackListUsersService iconditionOrderService;
//
//    @BeforeAll
//
//    static void createInstance(){
//        iconditionOrderService = BlackListUsersService.getInstance();
//    }
//
//    @Test
//    void testGetConditionOrdService() {
//        when(iconditionOrderDao.readConditionOrdByCond_idDao(anyInt())).thenReturn(null);
//        String result = iconditionOrderService.readConditionOrdByCond_idService(1);
//        Assertions.assertNull(result);
//    }
//}