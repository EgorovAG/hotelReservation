package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.СlientService;

public class DefaultClientService implements СlientService {

    ClientDao clientDao = DefaultClientDao.getInstance();
    private static volatile СlientService instance;

    public static СlientService getInstance() {
        СlientService localInstance = instance;
        if (localInstance == null) {
            synchronized (СlientService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultClientService();
                }
            }
        }
        return localInstance;
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

    @Override
    public AuthUser saveAuthUserAndClient(AuthUser authUser, Client client) {
        return clientDao.saveAuthUserAndClientDao(authUser, client);
    }

    @Override
    public boolean deleteAuthUserAndClientByUserIdDao(Integer userId) {
        return clientDao.deleteAuthUserAndClientByUserIdDao(userId);
    }




}




//    @Override
//    public int loadClient_id(String login) {
//        return 0;
//    }
