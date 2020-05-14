package com.github.egorovag.hotelreserv.dao;


import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClientDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private AuthUser authUser;
    private Client client;


    @BeforeEach
    void createAuthUserAndClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
    }

    @Test
    void testSaveAuthUserAndClientDao(){
        authUser = clientDao.saveAuthUserAndClientDao(authUser,client);
        Assertions.assertNotNull(authUser);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUser.getId());
    }

    @Test
    void testDeleteAuthUserAndClientByUserIdDao(){
        authUser = clientDao.saveAuthUserAndClientDao(authUser,client);
        Assertions.assertNotNull(authUser.getClient());
        boolean res = clientDao.deleteAuthUserAndClientByUserIdDao(authUser.getId());
        Assertions.assertTrue(res);
    }
}