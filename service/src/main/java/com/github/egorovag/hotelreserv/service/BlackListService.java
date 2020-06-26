package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface BlackListService {

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    List<BlackListUsersDTO> readBlackListUsersDTO();

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    boolean deleteBlackListById(int id);

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    boolean saveBlackListByAuthUserId(int id);

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    boolean checkBlackListByUserId(int id);
}
