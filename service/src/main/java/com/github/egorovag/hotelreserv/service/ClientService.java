package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;

import java.sql.SQLException;

public interface ClientService {

    boolean deleteAuthUserAndClientByClientId(Integer userId);

    Client readClientByClientId(Integer id);



//    boolean saveClient(Client client) ;
//    boolean deleteClientById(int id);

//    int loadClient_id(String login);



}
