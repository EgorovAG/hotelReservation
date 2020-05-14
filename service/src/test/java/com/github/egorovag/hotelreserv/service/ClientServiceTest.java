package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private static ClientDao clientDao;

    @InjectMocks
    private static ClientService clientService;

    AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);
    Client client = new Client(11, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
    AuthUserWithClient authUserWithClient = new AuthUserWithClient(10,"alex","pass","Alex","Alexandrov", "alex@tut.by", "55555");

    @BeforeAll
    static void createInstance(){
        clientService = DefaultClientService.getInstance();
    }

    @Test
    void testSaveAuthUserAndClient() {
        when(clientDao.saveAuthUserAndClientDao(authUser,client)).thenReturn(authUser);
        AuthUser authUserRes = clientService.saveAuthUserAndClient(authUser,client);
        Assertions.assertEquals(authUser, authUserRes);
    }

    @Test
    void testDeleteAuthUserAndClientByUserIdDao() {
        when(clientDao.deleteAuthUserAndClientByUserIdDao(authUserWithClient.getId())).thenReturn(true);
        boolean res = clientService.deleteAuthUserAndClientByUserId(authUserWithClient.getId());
        Assertions.assertTrue(res);
    }
}