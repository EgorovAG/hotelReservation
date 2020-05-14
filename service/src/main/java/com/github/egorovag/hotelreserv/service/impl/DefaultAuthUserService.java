package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.AuthUserService;

import java.util.List;


public class DefaultAuthUserService implements AuthUserService {

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
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
        if (login.equals(authUserDao.checkLoginDao(login))) {
            return true;
        }
        return false;
    }

    @Override
    public AuthUser checkUser(String login, String password) {
        AuthUser authUser = authUserDao.readUserByLoginDao(login);
        if (authUser == null) {
            return null;
        } else {
            if (login.equals(authUserDao.checkLoginDao(login)) &&
                    password.equals(authUser.getPassword())) {
                return authUser;
            } else {
                return null;
            }
        }
    }

    @Override
    public Client readClientByAuthUserId(Integer id) {
        return authUserDao.readClientByAuthUserIdDao(id);
    }

    @Override
    public List<AuthUserWithClient> readListAuthUserWithClient() {
        return authUserDao.readListAuthUserWithClientDao();
    }

    @Override
    public List<AuthUserWithClient> readListAuthUserWithClientPagination(int currentPage, int maxResultsPage) {
        return authUserDao.readListAuthUserWithClientPaginationDao(currentPage, maxResultsPage);
    }

    @Override
    public int countAuthUserWithClient() {
        return authUserDao.countAuthUserWithClientDao();
    }




    //    @Override
//    public AuthUser saveAuthUser(String login, String password, Role role) {
//        return checkAuthUserDao.saveUserDao(login, password, role);
//    }


//    @Override
//    public boolean deleteUserById(int id) {
//        if (checkAuthUserDao.deleteUserByIdDao(id)) {
//            return true;
//        }
//        return false;
//    }


}