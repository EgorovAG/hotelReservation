package com.github.egorovag.hotelreserv.service.config;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.service.*;
import com.github.egorovag.hotelreserv.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public AuthUserService authUserService () {return new DefaultAuthUserService(daoConfig.authUserDao());
    }

    @Bean
    public BlackListUsersService blackListUsersService() { return new DefaultBlackListUsersService(daoConfig.blackListUsersDao());
    }

    @Bean
    public ClientService clientService() { return new DefaultClientService(daoConfig.clientDao());
    }

    @Bean
    public OrderService orderService() { return new DefaultOrderService(daoConfig.orderDao());
    }

    @Bean
    public RoomService roomService() { return new DefaultRoomService(daoConfig.roomDao());
    }

    @Bean
    public ServiceHotelService serviceHotelService() { return new DefaultServiceHotelService(daoConfig.serviceHotelDao());
    }
}
