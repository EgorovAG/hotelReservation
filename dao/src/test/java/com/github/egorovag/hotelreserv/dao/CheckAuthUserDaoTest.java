//package com.github.egorovag.hotelreserv.dao;
//
//import com.github.egorovag.hotelreserv.dao.api.IcheckAuthUserDao;
//import com.github.egorovag.hotelreserv.dao.api.IclientDao;
//import com.github.egorovag.hotelreserv.model.AuthUser;
//import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
//import com.github.egorovag.hotelreserv.model.Client;
//import com.github.egorovag.hotelreserv.model.api.Role;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class CheckAuthUserDaoTest {
//    private IcheckAuthUserDao icheckAuthUserDao = CheckAuthUserDao.getInstance();
//    private IclientDao iclientDao = ClientDao.getInstance();
//    private AuthUser authUser;
//
//    @BeforeEach
//    void saveUser(){
//        authUser = icheckAuthUserDao.saveUserDao("alex", "pass", Role.USER);
//    }
//
//    @AfterEach
//    void deleteUser(){
//        icheckAuthUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }
//
//
//    @Test
//    void testCheckLoginDao() {
//        String login = icheckAuthUserDao.checkLoginDao(authUser.getLogin());
//        Assertions.assertEquals("alex", login);
//    }
//
//    @Test
//    void testReadPasswordByLoginDao() {
//        String login = icheckAuthUserDao.checkLoginDao(authUser.getLogin());
//        String password = icheckAuthUserDao.readPasswordByLoginDao(login);
//        Assertions.assertEquals("pass", password);
//    }
//
//    @Test
//    void testSaveUserDao() {
//        Assertions.assertEquals("alex", authUser.getLogin());
//    }
//
//    @Test
//    void testReadUserByLoginDao() {
//        AuthUser authUserRes = icheckAuthUserDao.readUserByLoginDao("alex");
//        Assertions.assertEquals("alex", authUserRes.getLogin());
//        Assertions.assertEquals("pass", authUserRes.getPassword());
//    }
//
//
////    @Test
////    void readListClientDao() {
////        AuthUser authUser =icheckAuthUserDao.saveUserDao("alex", "pass", Role.USER);
////        Client client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
////        iclientDao.saveClientDao(client);
////        List<AuthUserWithClient> authUserListsRes = icheckAuthUserDao.readListClientDao();
////        Assertions.assertTrue(authUserListsRes.size()>=1);
////        iclientDao.deleteClientByClient_idDao(authUser.getId());
////    }
//}