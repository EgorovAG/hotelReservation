//package com.github.egorovag.hotelreserv.service;
//
//import com.github.egorovag.hotelreserv.dao.IclientDao;
//import com.github.egorovag.hotelreserv.model.Client;
//import com.github.egorovag.hotelreserv.service.api.IclientService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ClientServiceTest {
//
//    @Mock
//    private static IclientDao iclientDao;
//
//    @InjectMocks
//    private static IclientService iclientService;
//
//    @BeforeAll
//    static void createInstance(){
//        iclientService = ClientService.getInstance();
//    }
//
//    @Test
//    void testSaveClient() {
//        Client client = new Client("Alex","Qwerty", "qwe@qwe", "55555", 10);
//        when(iclientDao.saveClientDao(client)).thenReturn(true);
//        boolean result = iclientService.saveClient(client);
//        Assertions.assertTrue(result);
//    }
//}