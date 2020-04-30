package com.github.egorovag.hotelreserv.dao;


import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClientDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private AuthUser authUser;
    private Client client;

    @BeforeEach
    void saveUserAndNewClient(){
        authUser = authUserDao.saveUserDao("alex", "pass", Role.USER);
        client = new Client("Alex","Alexandrov","alex@tut.by","55555",authUser.getId());
    }

    @Test
    void testSaveClientDao() {
        boolean result = clientDao.saveClientDao(client);
        Assertions.assertTrue(result);
        clientDao.deleteClientByClientIdDao(authUser.getId());
        authUserDao.deleteUserByLoginDao(authUser.getLogin());
    }

    @Test
    void testDeleteClientDao(){
        clientDao.saveClientDao(client);
        boolean result = clientDao.deleteClientByClientIdDao(authUser.getId());
        authUserDao.deleteUserByLoginDao(authUser.getLogin());
        Assertions.assertTrue(result);
    }
}