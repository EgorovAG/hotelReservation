//package com.github.egorovag.hotelreserv.dao;
//
//import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
//import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
//import com.github.egorovag.hotelreserv.model.AuthUser;
//import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
//import com.github.egorovag.hotelreserv.model.Client;
//import com.github.egorovag.hotelreserv.model.Role;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//
//class AuthUserDaoTest {
//    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
//    private ClientDao clientDao = DefaultClientDao.getInstance();
//    private AuthUser authUser;
//
//    @BeforeEach
//    void saveUser(){
//        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
//    }
//
//    @AfterEach
//    void deleteUser(){
//        authUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }
//
//
//    @Test
//    void testCheckLoginDao() {
//        String login = authUserDao.checkLoginDao(authUser.getLogin());
//        Assertions.assertEquals("alex", login);
//    }
//
//
//    @Test
//    void testSaveUserDao() {
//        Assertions.assertEquals("alex", authUser.getLogin());
//    }
//
//    @Test
//    void testReadUserByLoginDao() {
//        AuthUser authUserRes = authUserDao.readUserByLoginDao("alex");
//        Assertions.assertEquals("alex", authUserRes.getLogin());
//        Assertions.assertEquals("pass", authUserRes.getPassword());
//    }
//
//    @Test
//    void readClientByLoginDao() {
//        Client client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
//        clientDao.saveClientDao(client);
//        Client clientRes = authUserDao.readClientByLoginDao("alex");
//        Assertions.assertEquals("Alex", clientRes.getFirstName());
//        Assertions.assertEquals("55555", clientRes.getPhone());
//        clientDao.deleteClientByClientIdDao(authUser.getId());
//    }
//
//
//    @Test
//    void readListClientDao() {
//
//        Client client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
//        clientDao.saveClientDao(client);
//        List<AuthUserWithClient> authUserListsRes = authUserDao.readListClientDao();
//        Assertions.assertEquals(1, authUserListsRes.size());
//        clientDao.deleteClientByClientIdDao(authUser.getId());
//    }
//
//    @Test
//   void deleteUserByLoginDao() {
//        AuthUser authUser = authUserDao.saveUserDao("mike", "pass", Role.USER);
//        boolean res = authUserDao.deleteUserByLoginDao(authUser.getLogin());
//        Assertions.assertTrue(res);
//    }
//
//    @Test
//    void deleteUserByIdDao() {
//        AuthUser authUser = authUserDao.saveUserDao("mike", "pass", Role.USER);
//        boolean res = authUserDao.deleteUserByIdDao(authUser.getId());
//        Assertions.assertTrue(res);
//
//
//    }
//
//}
//
//
//
//// не надо
////@Test
////    void testReadPasswordByLoginDao() {
////        String login = icheckAuthUserDao.checkLoginDao(authUser.getLogin());
////        String password = icheckAuthUserDao.readPasswordByLoginDao(login);
////        Assertions.assertEquals("pass", password);
////    }