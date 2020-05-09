package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultBlackListUsersDao;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;

import java.util.List;

public class DefaultBlackListUsersService implements BlackListUsersService {

    private BlackListUsersDao iblackListUsersDao = DefaultBlackListUsersDao.getInstance();
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
    public List<BlackListUsers> readBlackListUsersListsService() {
        return iblackListUsersDao.readBlackListUsersListsDao();
    }

    @Override
    public boolean deleteBlackListUserById(int id) {
        return iblackListUsersDao.deleteBlackListUserByIdDao(id);
    }

    @Override
    public boolean saveBlackListUserById(int id) {
        return iblackListUsersDao.saveBlackListUserDao(id);
    }

    @Override
    public boolean checkBlackUserByUserId(int id) {
        if (iblackListUsersDao.checkBlackUserByUserIdDao(id)==1){
            return true;
        } else {
            return false;
        }
    }
}
