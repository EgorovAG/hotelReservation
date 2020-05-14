package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;

import java.sql.SQLException;

public interface ClientService {

    AuthUser saveAuthUserAndClient(AuthUser authUser, Client client);
    boolean deleteAuthUserAndClientByUserId(Integer userId);

//    boolean saveClient(Client client) ;
//    boolean deleteClientById(int id);

//    int loadClient_id(String login);



}
