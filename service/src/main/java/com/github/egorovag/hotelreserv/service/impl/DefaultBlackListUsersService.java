package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;

import java.util.List;

public class DefaultBlackListUsersService implements BlackListUsersService {

    private BlackListUsersDao blackListUsersDao = DefaultBlackListUsersDao.getInstance();
    private static volatile BlackListUsersService instance;

    public static BlackListUsersService getInstance() {
        BlackListUsersService localInstance = instance;
        if (localInstance == null) {
            synchronized (BlackListUsersService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultBlackListUsersService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<BlackListUsers> readBlackListUsersLists() {
        return blackListUsersDao.readBlackListUsersListsDao();
    }

    @Override
    public boolean deleteBlackListUserById(int id) {
        return blackListUsersDao.deleteBlackListUserByIdDao(id);
    }

    @Override
    public boolean saveBlackListUserById(int userId) {
        return blackListUsersDao.saveBlackListUserByIdDao(userId);
    }

    @Override
    public boolean checkBlackUserByUserId(int id) {
        if (blackListUsersDao.checkBlackUserByUserIdDao(id)!=null){
            return true;
        } else {
            return false;
        }
    }
}
