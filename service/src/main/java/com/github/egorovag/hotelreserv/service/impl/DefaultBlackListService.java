package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.BlackListDao;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultBlackListService implements BlackListService {

    private final BlackListDao blackListUsersDao;

    public DefaultBlackListService(BlackListDao blackListUsersDao) {
        this.blackListUsersDao = blackListUsersDao;
    }

    @Override
    @Transactional
    public List<BlackListUsers> readBlackListUsersLists() {
        return blackListUsersDao.readBlackListUsersListsDao();
    }

    @Override
    @Transactional
    public boolean deleteBlackListById(int id) {
        return blackListUsersDao.deleteBlackListByIdDao(id);
    }

    @Override
    @Transactional
    public boolean saveBlackListByAuthUserId(int userId) {
        return blackListUsersDao.saveBlackListByAuthUserIdDao(userId);
    }

    @Override
    @Transactional
    public boolean checkBlackListByUserId(int id) {
        if (blackListUsersDao.checkBlackUserByIdDao(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
