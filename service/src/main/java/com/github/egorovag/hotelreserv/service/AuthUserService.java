package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.model.Client;

import java.util.List;

public interface AuthUserService {

    AuthUser checkUser(String login, String password);

    boolean checkLogin(String login);

    List<AuthUserWithClientDTO> readListAuthUserWithClientDTO();

    List<AuthUserWithClientDTO> readListAuthUserWithClientDTOPagination(int currentPage, int maxResultsOnPage);

    int countAuthUserWithClient();

    AuthUser saveAuthUserAndClient(AuthUser authUser, Client client);
}
