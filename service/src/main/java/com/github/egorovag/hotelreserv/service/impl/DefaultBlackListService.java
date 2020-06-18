package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.BlackListDao;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import com.github.egorovag.hotelreserv.service.BlackListService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultBlackListService implements BlackListService {

    private final BlackListDao blackListDao;

    public DefaultBlackListService(BlackListDao blackListUsersDao) {
        this.blackListDao = blackListUsersDao;
    }

    @Override
    @Transactional
    public List<BlackListUsersDTO> readBlackListUsersDTO() {
        return blackListDao.readBlackListUsersDTODao();
    }

    @Override
    @Transactional
    public boolean deleteBlackListById(int id) {
        return blackListDao.deleteBlackListByIdDao(id);
    }

    @Override
    @Transactional
    public boolean saveBlackListByAuthUserId(int userId) {
        return blackListDao.saveBlackListByAuthUserIdDao(userId);
    }

    @Override
    @Transactional
    public boolean checkBlackListByUserId(int id) {
        if (blackListDao.checkBlackUserByIdDao(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
