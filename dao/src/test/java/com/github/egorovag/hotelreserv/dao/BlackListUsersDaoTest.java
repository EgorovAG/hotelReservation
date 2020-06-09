package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class BlackListUsersDaoTest {
    @Autowired
    BlackListUsersDao blackListUsersDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    SessionFactory sessionFactory;

    private AuthUser authUser;


    @BeforeEach
    void saveUserAndNewClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        Client client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = clientDao.saveAuthUserAndClientDao(authUser, client);
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