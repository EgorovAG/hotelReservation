package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.AuthUserDao;
import com.github.egorovag.hotelreserv.dao.converter.AuthUserConverter;
import com.github.egorovag.hotelreserv.dao.converter.ClientConverter;
import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.model.Client;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private final SessionFactory sessionFactory;

    public DefaultAuthUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override //criteria
    public String checkLoginDao(String login) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AuthUserEntity> criteria = cb.createQuery(AuthUserEntity.class);
            Root<AuthUserEntity> authUserEntityRoot = criteria.from(AuthUserEntity.class);
            criteria.select(authUserEntityRoot).where(cb.equal(authUserEntityRoot.get("login"), login));
            AuthUserEntity authUserEntityRes = session.createQuery(criteria).getSingleResult();
            log.info("authUser with login: {} readed", login);
            return authUserEntityRes.getLogin();
        } catch (NoResultException e) {
            log.error("Fail to read authUser with login: {}", login, e);
        }
        return null;
    }

    @Override // criteria
    public AuthUser readUserByLoginDao(String login) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AuthUserEntity> criteria = cb.createQuery(AuthUserEntity.class);
            Root<AuthUserEntity> authUserEntityRoot = criteria.from(AuthUserEntity.class);
            criteria.select(authUserEntityRoot).where(cb.equal(authUserEntityRoot.get("login"), login));
            AuthUserEntity authUserEntityRes = session.createQuery(criteria).getSingleResult();
            log.info("authuser with login: {} readed", login);
            return AuthUserConverter.fromEntity(authUserEntityRes);
        } catch (NoResultException e) {
            log.error("Fail to read authuser with login: {}", login, e);
            return null;
        }
    }

    @Override //criteria
    public Client readClientByAuthUserIdDao(Integer id) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AuthUserEntity> criteria = cb.createQuery(AuthUserEntity.class);
            Root<AuthUserEntity> authUserEntityRoot = criteria.from(AuthUserEntity.class);
            criteria.select(authUserEntityRoot).where(cb.equal(authUserEntityRoot.get("id"), id));
            AuthUserEntity authUserEntityRes = session.createQuery(criteria).getSingleResult();
            log.info("Client with authUserID:{} readed", id);
            return ClientConverter.fromEntity(authUserEntityRes.getClientEntity());
        } catch (HibernateError e) {
            log.error("Fail to read client with authUserID:{}", id, e);
            return null;
        }
    }

    @Override
    public List<AuthUserWithClient> readListAuthUserWithClientDao() {
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<AuthUserWithClient> listAU = session.createNativeQuery("select a.id,a.login,a.password,c.firstName," +
                    "c.secondName,c.email,c.phone from AuthUser a join Client c on a.id = c.user_id")
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("login", StandardBasicTypes.STRING)
                    .addScalar("password", StandardBasicTypes.STRING)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("phone", StandardBasicTypes.STRING)
                    .setResultTransformer(Transformers.aliasToBean(AuthUserWithClient.class))
                    .list();
            log.info("List<AuthUserWithClient> readed: {}", listAU);
            return listAU;
        } catch (HibernateException e) {
            log.error("Fail to read List<AuthUserWithClient>", e);
            return null;
        }
    }

    @Override
    public List<AuthUserWithClient> readListAuthUserWithClientPaginationDao(int currentPage, int maxResultsPage) {
        List<AuthUserWithClient> listAU = null;
        try {
            final Session session = sessionFactory.getCurrentSession();
            listAU = session.createNativeQuery("select a.id,a.login,a.password,c.firstName," +
                    "c.secondName,c.email,c.phone from AuthUser a join Client c on a.id = c.user_id")
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("login", StandardBasicTypes.STRING)
                    .addScalar("password", StandardBasicTypes.STRING)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("phone", StandardBasicTypes.STRING)
                    .setMaxResults(maxResultsPage)
                    .setFirstResult((currentPage - 1) * maxResultsPage)
                    .setResultTransformer(Transformers.aliasToBean(AuthUserWithClient.class))
                    .list();
            log.info("List<AuthUserWithClient> readed size: {}", listAU.size());
            return listAU;
        } catch (HibernateException e) {
            log.error("Fail to read List<AuthUserWithClient>", e);
            return null;
        }
    }

    @Override
    public int countAuthUserWithClientDao() {
        try {
            final Session session = sessionFactory.getCurrentSession();
            Long countResult = (Long) session.createQuery("SELECT COUNT(*) FROM ClientEntity")
                    .getSingleResult();
            log.info("CountAuthUserWithClient readed countResult = {}", countResult);
            return countResult.intValue();
        } catch (HibernateException e) {
            log.error("Fail to read CountAuthUserWithClient", e);
            return 0;
        }
    }
}