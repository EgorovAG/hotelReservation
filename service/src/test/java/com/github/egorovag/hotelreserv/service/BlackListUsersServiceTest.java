package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlackListUsersServiceTest {

    @Mock
    BlackListUsersDao blackListUsersDao;

    @InjectMocks
    DefaultBlackListUsersService defaultBlackListUsersService;

    private BlackListUsers blackListUsers = new BlackListUsers(5, 10, "Alex", "Alexandrov");
    private AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);

//    @BeforeAll
//    static void createInstance() {
//        blackListUsersService = DefaultBlackListUsersService.getInstance();
//    }

    @Test
    void readBlackListUsersLists() {
        List<BlackListUsers> blackListUsers = new ArrayList<>();
        when(blackListUsersDao.readBlackListUsersListsDao()).thenReturn(blackListUsers);
        List<BlackListUsers> blackListUsersRes = defaultBlackListUsersService.readBlackListUsersLists();
        Assertions.assertEquals(blackListUsers, blackListUsersRes);
    }

    @Test
    void deleteBlackListUserById() {
        when(blackListUsersDao.deleteBlackListUserByIdDao(blackListUsers.getId())).thenReturn(true);
        boolean res = defaultBlackListUsersService.deleteBlackListUserById(blackListUsers.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void saveBlackListUserById() {
        when(blackListUsersDao.saveBlackListUserByIdDao(authUser.getId())).thenReturn(true);
        boolean res = defaultBlackListUsersService.saveBlackListUserById(authUser.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void checkBlackUserByUserId() {
        when(blackListUsersDao.checkBlackUserByUserIdDao(authUser.getId())).thenReturn(null);
        boolean res = defaultBlackListUsersService.checkBlackUserByUserId(authUser.getId());
        Assertions.assertFalse(res);
    }
}