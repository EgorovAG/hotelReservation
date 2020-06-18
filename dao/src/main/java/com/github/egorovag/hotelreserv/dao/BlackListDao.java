package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;

import java.util.List;

public interface BlackListDao {
     List<BlackListUsersDTO> readBlackListUsersDTODao();
     boolean deleteBlackListByIdDao(int id);
     boolean saveBlackListByAuthUserIdDao(int id);
     Integer checkBlackUserByIdDao(int id);
}
