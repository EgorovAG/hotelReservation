package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.dao.api.IblackListUsersDao;
import com.github.egorovag.hotelreserv.model.BlackListUsers;
import com.github.egorovag.hotelreserv.service.api.IblackListUsersService;

import java.util.Date;
import java.util.List;

public class BlackListUsersService implements IblackListUsersService {

    private IblackListUsersDao iblackListUsersDao = BlackListUsersDao.getInstance();
    private static volatile IblackListUsersService instance;

    public static IblackListUsersService getInstance() {
        IblackListUsersService localInstance = instance;
        if (localInstance == null) {
            synchronized (IblackListUsersService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BlackListUsersService();
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
    public boolean checkBlackUserById(int id) {
        if (iblackListUsersDao.checkBlackUserByIdDao(id)){
            return true;
        } else {
            return false;
        }
    }
}
