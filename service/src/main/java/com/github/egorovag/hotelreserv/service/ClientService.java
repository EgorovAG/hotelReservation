package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.api.IclientDao;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;
import com.github.egorovag.hotelreserv.service.api.IclientService;

public class ClientService implements IclientService {

    IcheckUserService icheckUserService = CheckUserService.getInstance();
    IclientDao iclientDao = ClientDao.getInstance();
    private static volatile IclientService instance;

    public static IclientService getInstance() {
        IclientService localInstance = instance;
        if (localInstance == null) {
            synchronized (IclientService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ClientService();
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
