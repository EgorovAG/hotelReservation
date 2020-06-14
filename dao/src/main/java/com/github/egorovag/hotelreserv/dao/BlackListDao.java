package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;

import java.util.List;

public interface BlackListDao {
     List<BlackListUsers> readBlackListUsersListsDao();
     boolean deleteBlackListByIdDao(int id);
     boolean saveBlackListByAuthUserIdDao(int id);
     Integer checkBlackUserByIdDao(int id);
}
