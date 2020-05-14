package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;

import java.util.List;

public interface AuthUserDao {

    String checkLoginDao(String login);
    AuthUser readUserByLoginDao(String login);
    Client readClientByAuthUserIdDao(Integer id);
    List<AuthUserWithClient> readListAuthUserWithClientDao();
    List<AuthUserWithClient> readListAuthUserWithClientPaginationDao(int currentPage, int maxResultsPage);
    int countAuthUserWithClientDao();


//    boolean deleteUserByLoginDao(String login);
//    boolean deleteUserByIdDao(int id);
//    String readPasswordByLoginDao(String login);
//    AuthUser saveUserDao(String login, String password, Role role);

}
