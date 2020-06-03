package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.ClientService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultClientService implements ClientService {

    private final ClientDao clientDao;

    public DefaultClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public AuthUser saveAuthUserAndClient(AuthUser authUser, Client client) {
        return clientDao.saveAuthUserAndClientDao(authUser, client);
    }

    @Override
    @Transactional
    public boolean deleteAuthUserAndClientByUserId(Integer userId) {
        return clientDao.deleteAuthUserAndClientByUserIdDao(userId);
    }
}


//    @Override
//    public boolean saveClient(Client client) {
//        if (clientDao.saveClientDao(client)) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteClientById(int id) {
//        if (clientDao.deleteClientByClientIdDao(id)) {
//            return true;
//        }
//        return false;
//    }


//    @Override
//    public int loadClient_id(String login) {
//        return 0;
//    }
