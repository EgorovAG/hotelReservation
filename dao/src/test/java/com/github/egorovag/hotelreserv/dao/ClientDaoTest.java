package com.github.egorovag.hotelreserv.dao;


import com.github.egorovag.hotelreserv.dao.config.DaoConfig;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class ClientDaoTest {
    @Autowired
    ClientDao clientDao;
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