package com.github.egorovag.hotelreserv.web.config;


import com.github.egorovag.hotelreserv.service.config.ServiceConfig;
import com.github.egorovag.hotelreserv.web.controllers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public BlackListUsersServlet blackListUsersServlet() {
        return new BlackListUsersServlet(serviceConfig.blackListUsersService());
    }

    @Bean
    public BlockUserServlet blockUserServlet() {
        return new BlockUserServlet(serviceConfig.blackListUsersService());
    }

    @Bean
    public CheckPayServlet checkPayServlet() {
        return new CheckPayServlet(serviceConfig.orderService());
    }

    @Bean
    public ClientOrderServlet clientOrderServlet() {
        return new ClientOrderServlet(serviceConfig.orderService(), serviceConfig.roomService(),
                serviceConfig.serviceHotelService());
    }

    @Bean
    public LoginServlet loginServlet() {
        return new LoginServlet(serviceConfig.authUserService(), serviceConfig.blackListUsersService());
    }

    @Bean
    public LogoutServlet logoutServlet() {
        return new LogoutServlet();
    }

    @Bean
    public OrderListServlet orderListServlet() {
        return new OrderListServlet(serviceConfig.orderService());
    }

    @Bean
    public PaginationRegistratedUsers paginationRegistratedUsers() {
        return new PaginationRegistratedUsers(serviceConfig.authUserService());
    }

    @Bean
    public RegisteredUsersServlet registeredUsersServlet() {
        return new RegisteredUsersServlet(serviceConfig.authUserService(), serviceConfig.clientService());
    }

    @Bean
    public RegistrationServlet registrationServlet() {
        return new RegistrationServlet(serviceConfig.authUserService(), serviceConfig.clientService());
    }

    @Bean
    public StatusOrder statusOrder() {
        return new StatusOrder(serviceConfig.orderService());
    }

    @Bean
    public ToPayOrderServlet toPayOrderServlet() {
        return new ToPayOrderServlet(serviceConfig.orderService());
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
