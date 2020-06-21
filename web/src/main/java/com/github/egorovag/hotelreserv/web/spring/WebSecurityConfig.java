package com.github.egorovag.hotelreserv.web.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/login","/registration","/toRegistrationJspx").permitAll()
                .antMatchers("/blackListUsers", "/blockUser","/orderList","/paginationRegistratedUsers").hasRole("ADMIN")
                .antMatchers("/clientOrder","/checkPay","/statusOrder","/toPayOrder","toClientOrderJsp").hasRole("USER")
                .anyRequest().authenticated();
    }
}
