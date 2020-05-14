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
    private static AuthUserDao authUserDao;

    @InjectMocks
    private static AuthUserService authUserService;

    AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);
    Client client = new Client(11, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);

    @BeforeAll
    static void createInstance() {
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Test
    void testCheckLogin() {
        when(authUserDao.checkLoginDao("alex")).thenReturn("alex");
        boolean result = authUserService.checkLogin("alex");
        Assertions.assertTrue(result);
    }

    @Test
    void checkUser() {
        when(authUserDao.checkLoginDao("alex")).thenReturn("alex");
        when(authUserDao.readUserByLoginDao("alex")).thenReturn(authUser);
        AuthUser result = authUserService.checkUser("alex", "pass");
        Assertions.assertEquals(authUser, result);
    }


    @Test
    void testReadClientByAuthUserId() {
        when(authUserDao.readClientByAuthUserIdDao(authUser.getId())).thenReturn(client);
        Client clientRes = authUserService.readClientByAuthUserId(authUser.getId());
        Assertions.assertEquals(client, clientRes);
    }

    @Test
    void testReadListClient() {
        List<AuthUserWithClient> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientDao()).thenReturn(authUserList);
        List<AuthUserWithClient> authUserListRes = authUserService.readListAuthUserWithClient();
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testReadListAuthUserWithClientPagination() {
        List<AuthUserWithClient> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientPaginationDao(1, 2)).thenReturn(authUserList);
        List<AuthUserWithClient> authUserListRes = authUserService.readListAuthUserWithClientPagination(1,2);
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testCountAuthUserWithClient(){
        when(authUserDao.countAuthUserWithClientDao()).thenReturn(10);
        int res = authUserService.countAuthUserWithClient();
        Assertions.assertEquals(10, res);
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