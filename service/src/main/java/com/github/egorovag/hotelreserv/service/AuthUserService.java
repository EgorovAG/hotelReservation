package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;

import java.util.List;

public interface AuthUserService {

    AuthUser checkUser(String login, String password);
    boolean checkLogin(String login) ;
    Client readClientByAuthUserId(Integer id);
    List<AuthUserWithClient> readListAuthUserWithClient();
    List<AuthUserWithClient> readListAuthUserWithClientPagination(int currentPage, int maxResultsOnPage);
    int countAuthUserWithClient();


//    boolean deleteUserById(int id );
//    AuthUser saveAuthUser(String login, String password, Role role) ;

}
