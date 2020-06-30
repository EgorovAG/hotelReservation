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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class ClientDaoTest {

    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    ClientDao clientDao;

    AuthUser authUser;
    private Client client;

    @BeforeEach
    void createAuthUserAndClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
        Assertions.assertNotNull(authUser.getClient());
    }

    @Test
    @Rollback(value = false)
    void testDeleteAuthUserAndClientByUserIdDao() {
        boolean res = clientDao.deleteAuthUserAndClientByClientIdDao(authUser.getClient().getId());
        Assertions.assertTrue(res);
    }

    @Test
    void testReadClientByAuthUserIdDao() {
        Client clientRes = clientDao.readClientByClientIdDao(authUser.getClient().getId());
        Assertions.assertEquals("Alex", clientRes.getFirstName());
        Assertions.assertEquals("55555", clientRes.getPhone());
    }
}