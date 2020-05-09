package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;

import java.util.List;

public interface BlackListUsersDao {
     List<BlackListUsers> readBlackListUsersListsDao();
     boolean deleteBlackListUserByIdDao(int id);
     boolean saveBlackListUserDao(int id);
     int checkBlackUserByUserIdDao(int id);
}
