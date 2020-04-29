package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.CheckUserService;
import com.github.egorovag.hotelreserv.service.СlientService;

public class DefaultClientService implements СlientService {

    CheckUserService icheckUserService = DefaultCheckUserService.getInstance();
    ClientDao iclientDao = DefaultClientDao.getInstance();
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

    @Override
    public boolean saveClient(Client client) {
        if (iclientDao.saveClientDao(client)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteClientSeviceById(int id) {
        if (iclientDao.deleteClientByClientIdDao(id)) {
            return true;
        }
        return false;
    }
}


//    @Override
//    public int loadClient_id(String login) {
//        return 0;
//    }
