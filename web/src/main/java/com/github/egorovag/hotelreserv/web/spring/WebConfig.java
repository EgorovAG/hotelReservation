package com.github.egorovag.hotelreserv.web.spring;


import com.github.egorovag.hotelreserv.service.config.ServiceConfig;
import com.github.egorovag.hotelreserv.web.controllers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public Start start(){
        return new Start();
    }
    @Bean
    public BlackListServlet blackListServlet() {
        return new BlackListServlet(serviceConfig.blackListService());
    }

    @Bean
    public BlockUserServlet blockUserServlet() {
        return new BlockUserServlet(serviceConfig.blackListService());
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
        return new LoginServlet(serviceConfig.authUserService(), serviceConfig.blackListService(), serviceConfig.clientService());
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
        return new RegistrationServlet(serviceConfig.authUserService());
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
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }
}
