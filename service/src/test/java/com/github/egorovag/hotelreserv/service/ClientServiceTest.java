package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientDao clientDao;

    @InjectMocks
    DefaultClientService defaultClientService;

    private AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);
    private Client client = new Client(11, "Alex", "Alexandrov", "alex@tut.by", "55555");
    AuthUserWithClient authUserWithClient = new AuthUserWithClient(10,"alex","pass","Alex",
            "Alexandrov", "alex@tut.by", "55555");



    @Test
    void testDeleteAuthUserAndClientByClientIdDao() {
        when(clientDao.deleteAuthUserAndClientByClientIdDao(authUserWithClient.getClientId())).thenReturn(true);
        boolean res = defaultClientService.deleteAuthUserAndClientByClientId(authUserWithClient.getClientId());
        Assertions.assertTrue(res);
    }

    @Test
    void testReadClientByClientId() {
        when(clientDao.readClientByClientIdDao(authUser.getId())).thenReturn(client);
        Client clientRes = defaultClientService.readClientByClientId(authUser.getId());
        Assertions.assertEquals(client, clientRes);
    }
}