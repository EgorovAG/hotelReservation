package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;

public interface ClientDao {
    AuthUser saveAuthUserAndClientDao(AuthUser authUser, Client client);
    boolean deleteAuthUserAndClientByUserIdDao(Integer userId);

//    boolean saveClientDao(Client client);
//    void loadDao();
//    boolean deleteClientByClientIdDao(int client_id);
//    boolean deleteClientByFirstNameDao(String firstName);
//    void updateDao();

}
