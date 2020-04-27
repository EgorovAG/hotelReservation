package com.github.egorovag.hotelreserv.service.api;

import com.github.egorovag.hotelreserv.model.Client;

import java.sql.SQLException;

public interface IclientService {

    boolean saveClient(Client client) ;
    boolean deleteClientSeviceById(int id);
//    int loadClient_id(String login);



}
