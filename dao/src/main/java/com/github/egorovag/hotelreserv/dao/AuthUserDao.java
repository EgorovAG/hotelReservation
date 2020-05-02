package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.Role;

import java.util.List;

public interface AuthUserDao {

    String checkLoginDao(String login);
//    String readPasswordByLoginDao(String login);
    AuthUser saveUserDao(String login, String password, Role role);
    AuthUser readUserByLoginDao(String login);
    Client readClientByLoginDao(String login);
    List<AuthUserWithClient> readListClientDao();
    boolean deleteUserByLoginDao(String login);
    boolean deleteUserByIdDao(int id);


}