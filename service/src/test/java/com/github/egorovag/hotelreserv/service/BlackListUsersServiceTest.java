package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.BlackListDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlackListUsersServiceTest {

    @Mock
    BlackListDao blackListUsersDao;

    @InjectMocks
    DefaultBlackListService defaultBlackListUsersService;

    private BlackListUsers blackListUsers = new BlackListUsers(5, 10, "Alex", "Alexandrov");
    private AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);

    @Test
    void readBlackListUsersLists() {
        List<BlackListUsers> blackListUsers = new ArrayList<>();
        when(blackListUsersDao.readBlackListUsersListsDao()).thenReturn(blackListUsers);
        List<BlackListUsers> blackListUsersRes = defaultBlackListUsersService.readBlackListUsersLists();
        Assertions.assertEquals(blackListUsers, blackListUsersRes);
    }

    @Test
    void deleteBlackListUserById() {
        when(blackListUsersDao.deleteBlackListByIdDao(blackListUsers.getId())).thenReturn(true);
        boolean res = defaultBlackListUsersService.deleteBlackListById(blackListUsers.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void saveBlackListUserById() {
        when(blackListUsersDao.saveBlackListByAuthUserIdDao(authUser.getId())).thenReturn(true);
        boolean res = defaultBlackListUsersService.saveBlackListByAuthUserId(authUser.getId());
        Assertions.assertTrue(res);
    }

    @Test
    void checkBlackUserByUserId() {
        when(blackListUsersDao.checkBlackUserByIdDao(authUser.getId())).thenReturn(null);
        boolean res = defaultBlackListUsersService.checkBlackListByUserId(authUser.getId());
        Assertions.assertFalse(res);
    }
}