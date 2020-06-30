package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Client;

public interface ClientService {

    boolean deleteAuthUserAndClientByClientId(Integer userId);

    Client readClientByClientId(Integer id);
}
