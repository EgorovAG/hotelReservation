package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.converter.ClientConverter;
import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.dao.entity.ClientEntity;
import com.github.egorovag.hotelreserv.dao.repository.ClientCrudRepository;
import com.github.egorovag.hotelreserv.model.Client;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class DefaultClientDao implements ClientDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultClientDao.class);

    //    private final SessionFactory sessionFactory;
//
//    public DefaultClientDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
    @Autowired
    ClientCrudRepository clientCrudRepository;

    //Spring Data
    @Override
    public boolean deleteAuthUserAndClientByClientIdDao(Integer clientId) {
        try {
            clientCrudRepository.deleteById(clientId);
            log.info("AuthUser and Client with : {} clientId deleted", clientId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete AuthUser and Client with : {} userId ", clientId, e);
            return false;
        }
    }

//    @Override
//    public boolean deleteAuthUserAndClientByUserIdDao(Integer userId) {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, userId);
//            session.delete(authUserEntity);
//            log.info("AuthUser with : {} userId and Client deleted", userId);
//            return true;
//        } catch (HibernateException e) {
//            log.error("Fail to delete AuthUser with : {} userId and Client ", userId, e);
//            return false;
//        }
//    }


    //Spring Data
    @Override //criteria
    public Client readClientByClientIdDao(Integer id) {
        try {
            ClientEntity clientEntityRes = clientCrudRepository.findById(id).orElse(null);
            log.info("Client with authUserID:{} readed", id);
            return ClientConverter.fromEntity(clientEntityRes);
        } catch (HibernateError e) {
            log.error("Fail to read client with authUserID:{}", id, e);
            return null;
        }
    }

//    @Override //criteria
//    public Client readClientByAuthUserIdDao(Integer id) {
//        try {
//            final Session session = sessionFactory.getCurrentSession();
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<AuthUserEntity> criteria = cb.createQuery(AuthUserEntity.class);
//            Root<AuthUserEntity> authUserEntityRoot = criteria.from(AuthUserEntity.class);
//            criteria.select(authUserEntityRoot).where(cb.equal(authUserEntityRoot.get("id"), id));
//            AuthUserEntity authUserEntityRes = session.createQuery(criteria).getSingleResult();
//            log.info("Client with authUserID:{} readed", id);
//            return ClientConverter.fromEntity(authUserEntityRes.getClientEntity());
//        } catch (HibernateError e) {
//            log.error("Fail to read client with authUserID:{}", id, e);
//            return null;
//        }
//    }
}