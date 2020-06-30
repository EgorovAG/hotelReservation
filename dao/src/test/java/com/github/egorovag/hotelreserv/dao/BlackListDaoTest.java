package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
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
class BlackListDaoTest {
    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    BlackListDao blackListUsersDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    SessionFactory sessionFactory;

    private AuthUser authUser;

    @BeforeEach
    void saveUserAndNewClient() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        Client client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
    }

    @Test
    void testReadBlackListUsersListsDao() {
        blackListUsersDao.saveBlackListByAuthUserIdDao(authUser.getId());
        List<BlackListUsersDTO> listBL = blackListUsersDao.readBlackListUsersDTODao();
        Assertions.assertEquals(1, listBL.size());
    }

    @Test
    void testDeleteBlackListByIdDao() {
        blackListUsersDao.saveBlackListByAuthUserIdDao(authUser.getId());
        List<BlackListUsersDTO> listBL = blackListUsersDao.readBlackListUsersDTODao();
        BlackListUsersDTO blackListUsers = listBL.get(0);
        int id = blackListUsers.getId();
        boolean res = blackListUsersDao.deleteBlackListByIdDao(id);
        Assertions.assertTrue(res);
    }

    @Test
    void testSaveBlackListDao() {
        boolean res = blackListUsersDao.saveBlackListByAuthUserIdDao(authUser.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void testCheckBlackUserByIdDao() {
        blackListUsersDao.saveBlackListByAuthUserIdDao(authUser.getId());
        Integer idRes = blackListUsersDao.checkBlackUserByIdDao(authUser.getId());
        Assertions.assertNotNull(idRes);
    }
}