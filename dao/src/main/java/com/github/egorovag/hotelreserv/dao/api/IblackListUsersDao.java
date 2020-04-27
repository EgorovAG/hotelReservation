package com.github.egorovag.hotelreserv.dao.api;

import com.github.egorovag.hotelreserv.model.BlackListUsers;

import java.util.Date;
import java.util.List;

public interface IblackListUsersDao {
     List<BlackListUsers> readBlackListUsersListsDao();
     boolean deleteBlackListUserByIdDao(int id);
     boolean saveBlackListUserDao(int id);
     boolean checkBlackUserByIdDao(int id);
}
