package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
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
class AuthUserServiceTest {

    @Mock
    AuthUserDao authUserDao;

    @InjectMocks
    DefaultAuthUserService defaultAuthUserService;

    private AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);
    private Client client = new Client(11, "Alex", "Alexandrov", "alex@tut.by", "55555");

    @Test
    void testCheckLogin() {
        when(authUserDao.checkLoginDao("alex")).thenReturn("alex");
        boolean result = defaultAuthUserService.checkLogin("alex");
        Assertions.assertTrue(result);
    }

    @Test
    void checkUser() {
        when(authUserDao.checkLoginDao("alex")).thenReturn("alex");
        when(authUserDao.readUserByLoginDao("alex")).thenReturn(authUser);
        AuthUser result = defaultAuthUserService.checkUser("alex", "pass");
        Assertions.assertEquals(authUser, result);
    }



    @Test
    void testReadListClient() {
        List<AuthUserWithClient> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientDao()).thenReturn(authUserList);
        List<AuthUserWithClient> authUserListRes = defaultAuthUserService.readListAuthUserWithClient();
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testReadListAuthUserWithClientPagination() {
        List<AuthUserWithClient> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientPaginationDao(1, 2)).thenReturn(authUserList);
        List<AuthUserWithClient> authUserListRes = defaultAuthUserService.readListAuthUserWithClientPagination(1,2);
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testCountAuthUserWithClient(){
        when(authUserDao.countAuthUserWithClientDao()).thenReturn(10);
        int res = defaultAuthUserService.countAuthUserWithClient();
        Assertions.assertEquals(10, res);
    }
    @Test
    void testSaveAuthUserAndClient() {
        when(authUserDao.saveAuthUserAndClientDao(authUser,client)).thenReturn(authUser);
        AuthUser authUserRes = defaultAuthUserService.saveAuthUserAndClient(authUser,client);
        Assertions.assertEquals(authUser, authUserRes);
    }



//
//    @Test
//    @Deprecated
//    void testReadClientByLoginService() {
//        Client client = new Client("Alexey","Petrov","qwe@qew.by","55555",10);
//        when(authUserDao.readClientByLoginDao("alex")).thenReturn(client);
//        Client result = authUserService.readClientByLoginService("alex");
//        Assertions.assertEquals(client,result);
//    }
//
//    @Test
//    void readListClient() {
//        when(authUserDao.readListClientDao()).thenReturn(null);
//        List<AuthUserWithClient> authUserList = authUserService.readListClient();
//        Assertions.assertNull(authUserList);
//    }

    //    @Test
//    void testSaveAuthUser() {
//        AuthUser authUser = new AuthUser(10,"alex","pass",Role.USER);
//        Client client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
//        when(clientDao.saveAuthUserAndClientDao(authUser, client).thenReturn(authUser);
//        AuthUser result = authUserService.saveAuthUser("alex","pass",Role.USER);
//        Assertions.assertEquals(authUser, result);
//    }
}