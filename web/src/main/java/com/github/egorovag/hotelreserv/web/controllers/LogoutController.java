package com.github.egorovag.hotelreserv.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {
//    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @GetMapping("/logout")
    public String doGet(HttpServletRequest rq) {
        SecurityContextHolder.clearContext();
        try {
            rq.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        return "index";
    }
}
