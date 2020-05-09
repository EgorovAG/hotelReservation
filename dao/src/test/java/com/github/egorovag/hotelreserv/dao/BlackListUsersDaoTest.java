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

//    @BeforeEach
//    void saveUserAndNewClient() {
//        authUser = new AuthUser("alex", "pass", Role.USER);
//        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
//       int clientId = clientDao.saveAuthUserAndClientDao(authUser,client);
//    }

//    @AfterEach
//    void deleteUserAndNewClient(){
//        clientDao.deleteClientByClientIdDao(clientId);
//        authUserDao.deleteUserByIdDao(clientId);
//        }

    @Test
    void testReadBlackListUsersListsDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        int clientId = clientDao.saveAuthUserAndClientDao(authUser,client);
        boolean res = blackListUsersDao.saveBlackListUserDao(clientId);
        List<BlackListUsers> listBL = blackListUsersDao.readBlackListUsersListsDao();
        BlackListUsers blackListUsers = listBL.get(0);
        Assertions.assertEquals(1, listBL.size());
        clientDao.deleteAuthUserAndClientByUserIdDao(clientId);

    }

    @Test
    void testDeleteBlackListUserByIdDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        int clientId = clientDao.saveAuthUserAndClientDao(authUser,client);
        blackListUsersDao.saveBlackListUserDao(clientId);
        List<BlackListUsers> listBL = blackListUsersDao.readBlackListUsersListsDao();
        BlackListUsers blackListUsers = listBL.get(0);
        int id = blackListUsers.getId();
        boolean res = blackListUsersDao.deleteBlackListUserByIdDao(id);
        Assertions.assertTrue(res);
        clientDao.deleteAuthUserAndClientByUserIdDao(clientId);

    }

    @Test
    void testSaveBlackListUserDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        int clientId = clientDao.saveAuthUserAndClientDao(authUser,client);
        boolean res = blackListUsersDao.saveBlackListUserDao(clientId);
        Assertions.assertTrue(res);
        clientDao.deleteAuthUserAndClientByUserIdDao(clientId);
    }

    @Test
    void testCheckBlackUserByIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        int clientId = clientDao.saveAuthUserAndClientDao(authUser,client);
        boolean res = blackListUsersDao.saveBlackListUserDao(clientId);
        Integer idRes = blackListUsersDao.checkBlackUserByUserIdDao(clientId);
        Assertions.assertNotNull(idRes);
        clientDao.deleteAuthUserAndClientByUserIdDao(clientId);

    }
}