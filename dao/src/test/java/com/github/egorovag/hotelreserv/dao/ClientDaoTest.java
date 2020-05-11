package com.github.egorovag.hotelreserv.dao;


import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ClientDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private AuthUser authUser;
    private Client client;


//    @Test
//    void testSaveClientDao() {
//        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
//        client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
//        boolean result = clientDao.saveClientDao(client);
//        Assertions.assertTrue(result);
//        clientDao.deleteClientByClientIdDao(authUser.getId());
//        authUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }
//
//    @Test
//    void testDeleteClientDao(){
//        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
//        client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
//        clientDao.saveClientDao(client);
//        boolean result = clientDao.deleteClientByClientIdDao(authUser.getId());
//        authUserDao.deleteUserByLoginDao(authUser.getLogin());
//        Assertions.assertTrue(result);
//    }

    @Test //oneToOne
    void testSaveAuthUserAndClientDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Assertions.assertNotNull(authUserRes);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());
    }

    @Test //oneToOne
    void testDeleteAuthUserAndClientByUserIdDao(){
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser,client);
        Assertions.assertNotNull(authUserRes.getClient());
        boolean res = clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());
        Assertions.assertTrue(res);
    }
}