package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.CheckUserService;

import java.util.List;


public class DefaultCheckUserService implements CheckUserService {

    private AuthUserDao icheckAuthUserDao = DefaultAuthUserDao.getInstance();
    private static volatile CheckUserService instance;

    public static CheckUserService getInstance() {
        CheckUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (CheckUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultCheckUserService();
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
        AuthUser authUser = icheckAuthUserDao.readUserByLoginDao(login);
        if (authUser == null) {
            return null;
        } else {
            if (login.equals(icheckAuthUserDao.checkLoginDao(login)) &&
                    password.equals(authUser.getPassword())) {
                return authUser;
            } else {
                return null;
            }
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