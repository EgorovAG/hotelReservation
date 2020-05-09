package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;

import java.util.List;

public interface BlackListUsersService {

    List<BlackListUsers> readBlackListUsersListsService();
    boolean deleteBlackListUserById(int id);
    boolean saveBlackListUserById(int id);
    boolean checkBlackUserByUserId(int id);
}
