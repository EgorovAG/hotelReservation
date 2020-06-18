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


    //Spring Data
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
//    @Override
//    public List<BlackListUsers> readBlackListUsersListsDao() {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            List<BlackListUsers> listBL = session.createNativeQuery("select  bl.id, bl.user_id as userId, " +
//                    "c.firstName ,c.secondName, bl.date_block as dateBlock  from blacklist bl join client c " +
//                    "on bl.user_id = c.user_id")
//                    .addScalar("id", StandardBasicTypes.INTEGER)
//                    .addScalar("userId", StandardBasicTypes.INTEGER)
//                    .addScalar("firstName", StandardBasicTypes.STRING)
//                    .addScalar("secondName", StandardBasicTypes.STRING)
//                    .addScalar("dateBlock", LocalDateType.INSTANCE)
//                    .setResultTransformer(Transformers.aliasToBean(BlackListUsers.class))
//                    .list();
//            log.info("List<AuthUser> readed:{}", listBL);
//            return listBL;
//        } catch (HibernateException e) {
//            log.error("Fail to read List<BlackListUsers>", e);
//            return null;
//        }
//    }

    //Spring Data
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


//    @Override
//    public boolean deleteBlackListUserByIdDao(int id) {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            BlackListEntity blackListEntity = session.get(BlackListEntity.class, id);
//            session.delete(blackListEntity);
//                log.info("User from blackList with id:{} deleted", id);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to delete user from blackList with id:{}", id);
//            return false;
//        }
//    }

    //Spring Data
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

//    @Override
//    public boolean saveBlackListUserByIdDao(int userId) {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, userId);
//            BlackListEntity blackListEntity = new BlackListEntity(userId, LocalDate.now(), authUserEntity);
//            authUserEntity.setBlackListEntity(blackListEntity);
//            session.saveOrUpdate(blackListEntity);
//            session.saveOrUpdate(authUserEntity);
//
//            log.info("Client with id:{} saved in blackList", userId);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to save client:{} in blackList", userId, e);
//            return false;
//        }
//    }

    //Spring Data
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

//    @Override
//    public Integer checkBlackUserByUserIdDao(int id) {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, id);
//            log.info("AuthUser with id:{} readed in blackList", id);
//            if (authUserEntity.getBlackListEntity() != null) {
//                return authUserEntity.getBlackListEntity().getId();
//            }
//        } catch (HibernateException e) {
//            log.error("Fail to readed AuthUSer:{} in blackList", id, e);
//        }
//        return null;
//    }
}
