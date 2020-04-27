package com.github.egorovag.hotelreserv.dao.api;

import com.github.egorovag.hotelreserv.model.Client;

public interface IclientDao {
    boolean saveClientDao(Client client);
//    void loadDao();
    boolean deleteClientByClientIdDao(int client_id);
//    boolean deleteClientByFirstNameDao(String firstName);
//    void updateDao();

}
