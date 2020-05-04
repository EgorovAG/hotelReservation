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
    int clientId;
    boolean res;

    @BeforeEach
    void saveUserAndNewClient() {
        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
        clientId = authUser.getId();
        client = new Client("Alex", "Alexandrov", "alex@tut.by", "55555", authUser.getId());
        clientDao.saveClientDao(client);
    }

    @AfterEach
    void deleteUserAndNewClient(){
        clientDao.deleteClientByClientIdDao(clientId);
        authUserDao.deleteUserByIdDao(clientId);
        }

    @Test
    void testReadBlackListUsersListsDao() {
        blackListUsersDao.saveBlackListUserDao(clientId);
        List<BlackListUsers> listBL = blackListUsersDao.readBlackListUsersListsDao();
        Assertions.assertEquals(1, listBL.size());
        blackListUsersDao.deleteBlackListUserByIdDao(clientId);

    }

    @Test
    void testDeleteBlackListUserByIdDao() {
        blackListUsersDao.saveBlackListUserDao(clientId);
        boolean res = blackListUsersDao.deleteBlackListUserByIdDao(clientId);
        Assertions.assertTrue(res);
    }

    @Test
    void testSaveBlackListUserDao() {
        boolean res = blackListUsersDao.saveBlackListUserDao(clientId);
        Assertions.assertTrue(res);
        blackListUsersDao.deleteBlackListUserByIdDao(clientId);
    }

    @Test
    void testCheckBlackUserByIdDao(){
        blackListUsersDao.saveBlackListUserDao(clientId);
        int count = blackListUsersDao.checkBlackUserByIdDao(clientId);
        Assertions.assertEquals(1,count);
        blackListUsersDao.deleteBlackListUserByIdDao(clientId);

    }
}