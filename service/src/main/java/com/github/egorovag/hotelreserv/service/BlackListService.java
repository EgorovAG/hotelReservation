package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;

import java.util.List;

public interface BlackListService {

    List<BlackListUsersDTO> readBlackListUsersDTO();
    boolean deleteBlackListById(int id);
    boolean saveBlackListByAuthUserId(int id);
    boolean checkBlackListByUserId(int id);
}
