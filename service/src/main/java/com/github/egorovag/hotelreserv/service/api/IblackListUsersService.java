package com.github.egorovag.hotelreserv.service.api;

import com.github.egorovag.hotelreserv.model.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;

import java.util.List;

public interface IblackListUsersService {

    List<BlackListUsers> readBlackListUsersListsService();
    boolean deleteBlackListUserById(int id);
    boolean saveBlackListUserById(int id);
    boolean checkBlackUserById(int id);
}
