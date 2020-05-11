package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class AuthUserDaoTest {
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUser authUser;
    private Client client;

//    @BeforeEach
//    void saveUser() {
//        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
//
//    }
//
//    @AfterEach
//    void deleteUser() {
//        authUserDao.deleteUserByLoginDao(authUser.getLogin());
//    }


    @Test
    void testCheckLoginDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser, client);
        String login = authUserDao.checkLoginDao(authUser.getLogin());
        Assertions.assertEquals("alex", login);
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }


    @Test
    void testReadUserByLoginDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser, client);
        AuthUser authUserResult = authUserDao.readUserByLoginDao("alex");
        Assertions.assertEquals("alex", authUserResult.getLogin());
        Assertions.assertEquals("pass", authUserResult.getPassword());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());

    }

    @Test
    void testReadClientByAuthUserIdDao() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser, client);
        Client clientRes = authUserDao.readClientByAuthUserIdDao(authUserRes.getId());
        Assertions.assertEquals("Alex", clientRes.getFirstName());
        Assertions.assertEquals("55555", clientRes.getPhone());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());
    }

    @Test
    void testReadListClientDao() {

        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555", authUser);
        AuthUser authUserRes = clientDao.saveAuthUserAndClientDao(authUser, client);
        List<AuthUserWithClient> authUserListsRes = authUserDao.readListClientDao();
        Assertions.assertEquals(1, authUserListsRes.size());
        clientDao.deleteAuthUserAndClientByUserIdDao(authUserRes.getId());
    }
}
//
//    @Test
//   void testDeleteUserByLoginDao() {
//        AuthUser authUser = authUserDao.saveUserDao("mike", "pass", Role.USER);
//        boolean res = authUserDao.deleteUserByLoginDao(authUser.getLogin());
//        Assertions.assertTrue(res);
//    }
//
//    @Test
//    void testDeleteUserByIdDao() {
//        AuthUser authUser = authUserDao.saveUserDao("mike", "pass", Role.USER);
//        boolean res = authUserDao.deleteUserByIdDao(authUser.getId());
//        Assertions.assertTrue(res);
//    }
//}

//    @Test
//    void testSaveUserDao() {
//        Assertions.assertEquals("alex", authUser.getLogin());
//    }


// не надо
//@Test
//    void testReadPasswordByLoginDao() {
//        String login = icheckAuthUserDao.checkLoginDao(authUser.getLogin());
//        String password = icheckAuthUserDao.readPasswordByLoginDao(login);
//        Assertions.assertEquals("pass", password);
//    }