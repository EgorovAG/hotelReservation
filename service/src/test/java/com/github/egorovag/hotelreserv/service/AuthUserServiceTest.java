package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Assertions;
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
        List<AuthUserWithClientDTO> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientDTODao()).thenReturn(authUserList);
        List<AuthUserWithClientDTO> authUserListRes = defaultAuthUserService.readListAuthUserWithClientDTO();
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testReadListAuthUserWithClientPagination() {
        List<AuthUserWithClientDTO> authUserList = new ArrayList<>();
        when(authUserDao.readListAuthUserWithClientDTOPaginationDao(1, 2)).thenReturn(authUserList);
        List<AuthUserWithClientDTO> authUserListRes = defaultAuthUserService.readListAuthUserWithClientDTOPagination(1, 2);
        Assertions.assertEquals(authUserList, authUserListRes);
    }

    @Test
    void testCountAuthUserWithClient() {
        when(authUserDao.countAuthUserWithClientDao()).thenReturn(10);
        int res = defaultAuthUserService.countAuthUserWithClient();
        Assertions.assertEquals(10, res);
    }

    @Test
    void testSaveAuthUserAndClient() {
        when(authUserDao.saveAuthUserAndClientDao(authUser, client)).thenReturn(authUser);
        AuthUser authUserRes = defaultAuthUserService.saveAuthUserAndClient(authUser, client);
        Assertions.assertEquals(authUser, authUserRes);
    }
}