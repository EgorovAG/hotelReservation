package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultBlackListUsersService implements BlackListUsersService {

    private final BlackListUsersDao blackListUsersDao;

    public DefaultBlackListUsersService(BlackListUsersDao blackListUsersDao) {
        this.blackListUsersDao = blackListUsersDao;
    }

    @Override
    @Transactional
    public List<BlackListUsers> readBlackListUsersLists() {
        return blackListUsersDao.readBlackListUsersListsDao();
    }

    @Override
    @Transactional
    public boolean deleteBlackListUserById(int id) {
        return blackListUsersDao.deleteBlackListUserByIdDao(id);
    }

    @Override
    @Transactional
    public boolean saveBlackListUserById(int userId) {
        return blackListUsersDao.saveBlackListUserByIdDao(userId);
    }

    @Override
    @Transactional
    public boolean checkBlackUserByUserId(int id) {
        if (blackListUsersDao.checkBlackUserByUserIdDao(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
