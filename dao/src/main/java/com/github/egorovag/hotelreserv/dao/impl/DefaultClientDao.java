package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
import com.github.egorovag.hotelreserv.dao.converter.AuthUserConverter;
import com.github.egorovag.hotelreserv.dao.converter.ClientConverter;
import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.dao.entity.ClientEntity;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultClientDao implements ClientDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultClientDao.class);
    private final SessionFactory sessionFactory;

    public DefaultClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AuthUser saveAuthUserAndClientDao(AuthUser authUser, Client client) {
        try {
            AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
            ClientEntity clientEntity = ClientConverter.toEntity(client);
            final Session session = sessionFactory.getCurrentSession();
            authUserEntity.setClientEntity(clientEntity);
            clientEntity.setAuthUserEntity(authUserEntity);
            int id = (int) session.save(authUserEntity);
            AuthUserEntity authUserEntityRes = session.get(AuthUserEntity.class, id);
            log.info("AuthUser: {} and Client : {} saved", authUser, client);
            return AuthUserConverter.fromEntity(authUserEntityRes);
        } catch (HibernateException e) {
            log.error("Fail to save AuthUser: {} and Client : {} ", authUser, client, e);
            return null;
        }
    }

    @Override
    public boolean deleteAuthUserAndClientByUserIdDao(Integer userId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, userId);
            session.delete(authUserEntity);
            log.info("AuthUser with : {} userId and Client deleted", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete AuthUser with : {} userId and Client ", userId, e);
            return false;
        }
    }
}