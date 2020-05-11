package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.AuthUserService;

import java.util.List;


public class DefaultAuthUserService implements AuthUserService {

    private AuthUserDao userDao = DefaultAuthUserDao.getInstance();
    private static volatile AuthUserService instance;

    public static AuthUserService getInstance() {
        AuthUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultAuthUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean checkLogin(String login) {
        if (login.equals(userDao.checkLoginDao(login))) {
            return true;
        }
        return false;
    }

    @Override
    public AuthUser checkUser(String login, String password) {
        AuthUser authUser = userDao.readUserByLoginDao(login);
        if (authUser == null) {
            return null;
        } else {
            if (login.equals(userDao.checkLoginDao(login)) &&
                    password.equals(authUser.getPassword())) {
                return authUser;
            } else {
                return null;
            }
        }
    }

//    @Override
//    public AuthUser saveAuthUser(String login, String password, Role role) {
//        return checkAuthUserDao.saveUserDao(login, password, role);
//    }

    @Override
    public Client readClientByAuthUserId(Integer id) {
        return userDao.readClientByAuthUserIdDao(id);
    }

    @Override
    public List<AuthUserWithClient> readListClient() {
        return userDao.readListClientDao();
    }

//    @Override
//    public boolean deleteUserById(int id) {
//        if (checkAuthUserDao.deleteUserByIdDao(id)) {
//            return true;
//        }
//        return false;
//    }
}