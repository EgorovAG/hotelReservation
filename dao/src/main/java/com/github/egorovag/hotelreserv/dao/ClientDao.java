package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;

public interface ClientDao {

    boolean deleteAuthUserAndClientByClientIdDao(Integer ClientId);

    Client readClientByClientIdDao(Integer id);
}
