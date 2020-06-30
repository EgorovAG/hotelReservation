package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.BlackListDao;
import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.dao.entity.BlackListEntity;
import com.github.egorovag.hotelreserv.dao.repository.AuthUserJpaRepository;
import com.github.egorovag.hotelreserv.dao.repository.BlackListJpaRepository;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


public class DefaultBlackListDao implements BlackListDao {

    @Autowired
    BlackListJpaRepository blackListJpaRepository;

    @Autowired
    AuthUserJpaRepository authUserJpaRepository;

    private static final Logger log = LoggerFactory.getLogger(DefaultBlackListDao.class);

    @Override
    public List<BlackListUsersDTO> readBlackListUsersDTODao() {
        try {
            List<BlackListUsersDTO> listBL = blackListJpaRepository.readBlackListUsersListsDaoSD();
            log.info("List<AuthUser> readed:{}", listBL);
            return listBL;
        } catch (HibernateException e) {
            log.error("Fail to read List<BlackListUsers>", e);
            return null;
        }
    }

    @Override
    public boolean deleteBlackListByIdDao(int id) {
        try {
            blackListJpaRepository.deleteById(id);
            log.info("User from blackList with id:{} deleted", id);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete user from blackList with id:{}", id);
            return false;
        }
    }

    @Override
    public boolean saveBlackListByAuthUserIdDao(int userId) {
        try {
            AuthUserEntity authUserEntity = authUserJpaRepository.findById(userId).orElse(null);
            BlackListEntity blackListEntity = new BlackListEntity(userId, LocalDate.now(), authUserEntity);
            if (authUserEntity != null)
                authUserEntity.setBlackListEntity(blackListEntity);
            authUserJpaRepository.save(authUserEntity);
            log.info("Client with id:{} saved in blackList", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to save client:{} in blackList", userId, e);
            return false;
        }
    }

    @Override
    public Integer checkBlackUserByIdDao(int id) {
        try {

            BlackListEntity blackListEntity = blackListJpaRepository.findBlackListEntityByUserId(id);
            log.info("AuthUser with id:{} readed in blackList", id);

            if (blackListEntity != null) {
                return blackListEntity.getId();
            }
        } catch (
                HibernateException e) {
            log.error("Fail to readed AuthUSer:{} in blackList", id, e);
        }
        return null;
    }
}
