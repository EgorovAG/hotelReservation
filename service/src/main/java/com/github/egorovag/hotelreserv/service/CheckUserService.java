package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.Role;

import java.util.List;

public interface CheckUserService {

    AuthUser checkUser(String login, String password);
    boolean checkLogin(String login) ;
    AuthUser saveAuthUser(String login, String password, Role role) ;
    Client readClientByLoginService(String login);
    List<AuthUserWithClient> readListClient();
    boolean deleteUserByIdService( int id );
}
