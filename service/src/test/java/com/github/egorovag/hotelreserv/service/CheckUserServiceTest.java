//package com.github.egorovag.hotelreserv.service;
//
//import com.github.egorovag.hotelreserv.dao.IcheckAuthUserDao;
//import com.github.egorovag.hotelreserv.model.AuthUser;
//import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
//import com.github.egorovag.hotelreserv.model.Client;
//import com.github.egorovag.hotelreserv.model.Role;
//import com.github.egorovag.hotelreserv.service.api.IcheckUserService;
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
//@ExtendWith(MockitoExtension.class)
//class CheckUserServiceTest {
//
//    @Mock
//    private static IcheckAuthUserDao icheckAuthUserDao;
//
//    @InjectMocks
//    private static IcheckUserService icheckUserService;
//
//    @BeforeAll
//    static void createInstance(){
//        icheckUserService = CheckUserService.getInstance();
//    }
//
//    @Test
//    void testCheckLogin() {
//        when(icheckAuthUserDao.checkLoginDao("alex")).thenReturn("alex");
//        boolean result = icheckUserService.checkLogin("alex");
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    void checkUser() {
//        AuthUser authUser = new AuthUser(10,"alex","pass",Role.USER);
//        when(icheckAuthUserDao.checkLoginDao("alex")).thenReturn("alex");
//        when(icheckAuthUserDao.readPasswordByLoginDao("alex")).thenReturn("pass");
//        when(icheckAuthUserDao.readUserByLoginDao("alex")).thenReturn(authUser);
//        AuthUser result = icheckUserService.checkUser("alex","pass");
//        Assertions.assertEquals(authUser, result);
//    }
//
//    @Test
//    void testSaveAuthUser() {
//        AuthUser authUser = new AuthUser(10,"alex","pass",Role.USER);
//        when(icheckAuthUserDao.saveUserDao("alex","pass", Role.USER)).thenReturn(authUser);
//        AuthUser result = icheckUserService.saveAuthUser("alex","pass",Role.USER);
//        Assertions.assertEquals(authUser, result);
//    }
//
//    @Test
//    @Deprecated
//    void testReadClientByLoginService() {
//        Client client = new Client("Alexey","Petrov","qwe@qew.by","55555",10);
//        when(icheckAuthUserDao.readClientByLoginDao("alex")).thenReturn(client);
//        Client result = icheckUserService.readClientByLoginService("alex");
//        Assertions.assertEquals(client,result);
//    }
//
//    @Test
//    void readListClient() {
//        when(icheckAuthUserDao.readListClientDao()).thenReturn(null);
//        List<AuthUserWithClient> authUserList = icheckUserService.readListClient();
//        Assertions.assertNull(authUserList);
//    }
//}