package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.CheckAuthUserDao;
import com.github.egorovag.hotelreserv.dao.api.IcheckAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.api.Role;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;

import java.util.List;


public class CheckUserService implements IcheckUserService {

    private IcheckAuthUserDao icheckAuthUserDao = CheckAuthUserDao.getInstance();
    private static volatile IcheckUserService instance;

    public static IcheckUserService getInstance() {
        IcheckUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (IcheckUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CheckUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean checkLogin(String login) {
        if (login.equals(icheckAuthUserDao.checkLoginDao(login))) {
            return true;
        }
        return false;
    }

    @Override
    public AuthUser checkUser(String login, String password) {
        if (login.equals(icheckAuthUserDao.checkLoginDao(login)) &&
                password.equals(icheckAuthUserDao.readPasswordByLoginDao(login))) {
            return icheckAuthUserDao.readUserByLoginDao(login);
        } else {
            return null;
        }
    }

    @Override
    public AuthUser saveAuthUser(String login, String password, Role role) {
        return icheckAuthUserDao.saveUserDao(login, password, role);
    }

    @Override
    public Client readClientByLoginService(String login) {
        return icheckAuthUserDao.readClientByLoginDao(login);
    }

    @Override
    public List<AuthUserWithClient> readListClient() {
        return icheckAuthUserDao.readListClientDao();
    }

    @Override
    public boolean deleteUserByIdService(int id) {
        if (icheckAuthUserDao.deleteUserByIdDao(id)) {
            return true;
        }
        return false;
    }
}