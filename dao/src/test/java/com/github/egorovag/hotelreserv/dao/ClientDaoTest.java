//package com.github.egorovag.hotelreserv.dao;
//
//
//import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
//import com.github.egorovag.hotelreserv.model.AuthUser;
//import com.github.egorovag.hotelreserv.model.Client;
//import com.github.egorovag.hotelreserv.model.Role;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//@Deprecated
//class ClientDaoTest {
//    private ClientDao iclientDao = DefaultClientDao.getInstance();
//    private AuthUserDao icheckAuthUserDao = DefaultAuthUserDao.getInstance();
//    private AuthUser authUser;
//    private Client client;
//
//    @BeforeEach
//    void saveUserAndNewClient(){
//        authUser =icheckAuthUserDao.saveUserDao("alex", "pass", Role.USER);
//        client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
//    }
//
//    @Test
//    void testSaveClientDao() {
//        boolean result = iclientDao.saveClientDao(client);
//        Assertions.assertTrue(result);
//        iclientDao.deleteClientByClientIdDao(authUser.getId());
//        icheckAuthUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }
//
//    @Test
//    void testDeleteClientDao(){
//        iclientDao.saveClientDao(client);
//        boolean result = iclientDao.deleteClientByClientIdDao(authUser.getId());
//        icheckAuthUserDao.deleteUserByLoginDao(authUser.getLogin());
//        Assertions.assertTrue(result);
//    }
//}