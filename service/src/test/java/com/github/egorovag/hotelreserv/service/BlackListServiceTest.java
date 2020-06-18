package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.BlackListDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
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
class BlackListServiceTest {

    @Mock
    BlackListDao blackListUsersDao;

    @InjectMocks
    DefaultBlackListService defaultBlackListUsersService;

    private BlackListUsersDTO blackListUsers = new BlackListUsersDTO(5, 10, "Alex", "Alexandrov");
    private AuthUser authUser = new AuthUser(10, "alex", "pass", Role.USER);

    @Test
    void readBlackListUsersLists() {
        List<BlackListUsersDTO> blackListUsers = new ArrayList<>();
        when(blackListUsersDao.readBlackListUsersDTODao()).thenReturn(blackListUsers);
        List<BlackListUsersDTO> blackListUsersRes = defaultBlackListUsersService.readBlackListUsersDTO();
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