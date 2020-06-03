package com.github.egorovag.hotelreserv.dao.impl;

import com.github.egorovag.hotelreserv.dao.BlackListUsersDao;
import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.BlackList;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LocalDateType;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class DefaultBlackListUsersDao implements BlackListUsersDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultBlackListUsersDao.class);
    private final SessionFactory sessionFactory;

    public DefaultBlackListUsersDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BlackListUsers> readBlackListUsersListsDao() {
        try {
            final Session session = sessionFactory.getCurrentSession();
            List<BlackListUsers> listBL = session.createNativeQuery("select  bl.id, bl.user_id as userId, " +
                    "c.firstName ,c.secondName, bl.date_block as dateBlock  from blacklist bl join client c " +
                    "on bl.user_id = c.user_id")
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("userId", StandardBasicTypes.INTEGER)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("dateBlock", LocalDateType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(BlackListUsers.class))
                    .list();
            log.info("List<AuthUser> readed:{}", listBL);
            return listBL;
        } catch (HibernateException e) {
            log.error("Fail to read List<BlackListUsers>", e);
            return null;
        }
    }

    @Override
    public boolean deleteBlackListUserByIdDao(int id) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            BlackList blackList = session.get(BlackList.class, id);
            session.delete(blackList);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to delete user from blackList with id:{}", id);
            return false;
        }
    }

    @Override
    public boolean saveBlackListUserByIdDao(int userId) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            AuthUser authUser = session.get(AuthUser.class, userId);
            BlackList blackList = new BlackList(userId, LocalDate.now(), authUser);
            session.saveOrUpdate(blackList);
            log.info("Client with id:{} saved in blackList", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Fail to save client:{} in blackList", userId, e);
            return false;
        }
    }

    @Override
    public Integer checkBlackUserByUserIdDao(int id) {
        try {
            final Session session = sessionFactory.getCurrentSession();
            AuthUser authUser = session.get(AuthUser.class, id);
            log.info("AuthUser with id:{} readed in blackList", id);
            if (authUser.getBlackList() != null) {
                return authUser.getBlackList().getId();
            }
        } catch (HibernateException e) {
            log.error("Fail to readed AuthUSer:{} in blackList", id, e);
        }
        return null;
    }
}
