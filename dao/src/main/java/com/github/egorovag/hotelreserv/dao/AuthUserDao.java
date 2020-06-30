package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.model.Client;

import java.util.List;

public interface AuthUserDao {

    String checkLoginDao(String login);

    AuthUser readUserByLoginDao(String login);

    List<AuthUserWithClientDTO> readListAuthUserWithClientDTODao();

    List<AuthUserWithClientDTO> readListAuthUserWithClientDTOPaginationDao(int currentPage, int maxResultsPage);

    int countAuthUserWithClientDao();

    AuthUser saveAuthUserAndClientDao(AuthUser authUser, Client client);
}
