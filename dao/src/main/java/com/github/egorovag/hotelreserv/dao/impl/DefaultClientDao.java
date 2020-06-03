package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.ClientDao;
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
            final Session session = sessionFactory.getCurrentSession();
            authUser.setClient(client);
            int id = (int) session.save(authUser);
            AuthUser authUserRes = session.get(AuthUser.class, id);
            log.info("AuthUser: {} and Client : {} saved", authUser, client);
            return authUserRes;
        } catch (HibernateException e) {
            log.error("Fail to save AuthUser: {} and Client : {} ", authUser, client, e);
            return null;
        }
    }

    @Override
    public boolean deleteAuthUserAndClientByUserIdDao(Integer userId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            AuthUser authUser = session.get(AuthUser.class, userId);
            session.delete(authUser);
            log.info("AuthUser with : {} userId and Client deleted", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete AuthUser with : {} userId and Client ", userId, e);
            return false;
        }
    }
}