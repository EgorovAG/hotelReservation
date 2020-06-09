package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
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

    @BeforeEach
    void saveAuthUserAndClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        Client client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = clientDao.saveAuthUserAndClientDao(authUser, client);

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
    void testReadClientByAuthUserIdDao() {
        Client clientRes = authUserDao.readClientByAuthUserIdDao(authUser.getId());
        Assertions.assertEquals("Alex", clientRes.getFirstName());
        Assertions.assertEquals("55555", clientRes.getPhone());
    }

    @Test
    void testReadListClientDao() {
        List<AuthUserWithClient> authUserListsRes = authUserDao.readListAuthUserWithClientDao();
        Assertions.assertEquals(1, authUserListsRes.size());
    }

    @Test
    void testReadListAuthUserWithClientPaginationDao() {
        int firstResultPage = 1;
        int maxResultsPage = 5;
        List<AuthUserWithClient> authUserListsRes = authUserDao.readListAuthUserWithClientPaginationDao(firstResultPage, maxResultsPage);
        Assertions.assertEquals(1, authUserListsRes.size());
    }

    @Test
    void testCountAuthUserWithClientDao(){
        int countResult = authUserDao.countAuthUserWithClientDao();
        Assertions.assertEquals(1,countResult);
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