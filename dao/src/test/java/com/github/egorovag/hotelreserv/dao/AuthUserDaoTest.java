package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
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

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class AuthUserDaoTest {
    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    ClientDao clientDao;

    private AuthUser authUser;
    private Client client;

    @BeforeEach
    void saveAuthUserAndClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
    }

    @Test
    void testCheckLoginDao() {
        String login = authUserDao.checkLoginDao(authUser.getLogin());
        Assertions.assertEquals("alex", login);
    }

    @Test
    void testReadUserByLoginDao() {
        AuthUser authUserResult = authUserDao.readUserByLoginDao("alex");
        Assertions.assertEquals("alex", authUserResult.getLogin());
        Assertions.assertEquals("pass", authUserResult.getPassword());
    }

    @Test
    void testReadListClientDao() {
        List<AuthUserWithClientDTO> authUserListsRes = authUserDao.readListAuthUserWithClientDTODao();
        Assertions.assertEquals(1, authUserListsRes.size());
    }

    @Test
    void testReadListAuthUserWithClientPaginationDao() {
        int firstResultPage = 1;
        int maxResultsPage = 3;
        List<AuthUserWithClientDTO> authUserListsRes = authUserDao.readListAuthUserWithClientDTOPaginationDao(firstResultPage, maxResultsPage);
        Assertions.assertEquals(1, authUserListsRes.size());
    }

    @Test
    void testCountAuthUserWithClientDao() {
        int countResult = authUserDao.countAuthUserWithClientDao();
        Assertions.assertEquals(1, countResult);
    }

    @Test
    void testSaveAuthUserAndClientDao() {
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
        Assertions.assertNotNull(authUser);
    }
}