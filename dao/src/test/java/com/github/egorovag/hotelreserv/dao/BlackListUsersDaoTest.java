package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.*;

import java.util.List;

class BlackListUsersDaoTest {
    BlackListUsersDao blackListUsersDao = DefaultBlackListUsersDao.getInstance();
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private AuthUser authUser;
    private Client client;


    @BeforeEach
    void saveUserAndNewClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
        authUser = clientDao.saveAuthUserAndClientDao(authUser, client);
    }

    @AfterEach
    void deleteUserAndNewClient() {
        clientDao.deleteAuthUserAndClientByUserIdDao(authUser.getId());
    }

    @Test
    void testReadBlackListUsersListsDao() {
        blackListUsersDao.saveBlackListUserByIdDao(authUser.getId());
        List<BlackListUsers> listBL = blackListUsersDao.readBlackListUsersListsDao();
        listBL.get(0);
        Assertions.assertEquals(1, listBL.size());
    }

    @Test
    void testDeleteBlackListUserByIdDao() {
        blackListUsersDao.saveBlackListUserByIdDao(authUser.getId());
        List<BlackListUsers> listBL = blackListUsersDao.readBlackListUsersListsDao();
        BlackListUsers blackListUsers = listBL.get(0);
        int id = blackListUsers.getId();
        boolean res = blackListUsersDao.deleteBlackListUserByIdDao(id);
        Assertions.assertTrue(res);
    }

    @Test
    void testSaveBlackListUserDao() {
        boolean res = blackListUsersDao.saveBlackListUserByIdDao(authUser.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void testCheckBlackUserByIdDao() {
        blackListUsersDao.saveBlackListUserByIdDao(authUser.getId());
        Integer idRes = blackListUsersDao.checkBlackUserByUserIdDao(authUser.getId());
        Assertions.assertNotNull(idRes);
    }
}