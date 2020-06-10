package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class DefaultAuthUserService implements AuthUserService {

    private final AuthUserDao authUserDao;

    public DefaultAuthUserService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }


    @Override
    @Transactional
    public boolean checkLogin(String login) {
        return login.equals(authUserDao.checkLoginDao(login));
    }

    @Override
    @Transactional
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
    @Transactional
    public Client readClientByAuthUserId(Integer id) {
        return authUserDao.readClientByAuthUserIdDao(id);
    }

    @Override
    @Transactional
    public List<AuthUserWithClient> readListAuthUserWithClient() {
        return authUserDao.readListAuthUserWithClientDao();
    }

    @Override
    @Transactional
    public List<AuthUserWithClient> readListAuthUserWithClientPagination(int currentPage, int maxResultsPage) {
        return authUserDao.readListAuthUserWithClientPaginationDao(currentPage, maxResultsPage);
    }

    @Override
    @Transactional
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