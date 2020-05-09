package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.UserService;

import java.util.List;


public class DefaultUserService implements UserService {

    private AuthUserDao checkAuthUserDao = DefaultAuthUserDao.getInstance();
    private static volatile UserService instance;

    public static UserService getInstance() {
        UserService localInstance = instance;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean checkLogin(String login) {
        if (login.equals(checkAuthUserDao.checkLoginDao(login))) {
            return true;
        }
        return false;
    }

    @Override
    public AuthUser checkUser(String login, String password) {
        AuthUser authUser = checkAuthUserDao.readUserByLoginDao(login);
        if (authUser == null) {
            return null;
        } else {
            if (login.equals(checkAuthUserDao.checkLoginDao(login)) &&
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
        return checkAuthUserDao.readClientByAuthUserIdDao(id);
    }

    @Override
    public List<AuthUserWithClient> readListClient() {
        return checkAuthUserDao.readListClientDao();
    }

//    @Override
//    public boolean deleteUserById(int id) {
//        if (checkAuthUserDao.deleteUserByIdDao(id)) {
//            return true;
//        }
//        return false;
//    }
}