package com.github.egorovag.hotelreserv.dao.config;


import com.github.egorovag.hotelreserv.dao.*;
import com.github.egorovag.hotelreserv.dao.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public AuthUserDao authUserDao() {
        return new DefaultAuthUserDao();
    }

    @Bean
    public BlackListDao blackListDao() {
        return new DefaultBlackListDao();
    }

    @Bean
    public ClientDao clientDao() {
        return new DefaultClientDao();
    }

    @Bean
    public OrderDao orderDao() {
        return new DefaultOrderDao(sessionFactory);
    }

    @Bean
    public RoomDao roomDao() {
        return new DefaultRoomDao(sessionFactory);
    }

    @Bean
    public ServiceHotelDao serviceHotelDao() {
        return new DefaultServiceHotelDao(sessionFactory);
    }
}
