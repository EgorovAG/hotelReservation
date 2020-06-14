package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;

public interface ClientDao {
    boolean deleteAuthUserAndClientByClientIdDao(Integer ClientId);

    Client readClientByClientIdDao(Integer id);


//    boolean saveClientDao(Client client);
//    void loadDao();
//    boolean deleteClientByClientIdDao(int client_id);
//    boolean deleteClientByFirstNameDao(String firstName);
//    void updateDao();

}
