package com.github.egorovag.hotelreserv.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @GetMapping("/logout")
    public String doGet(HttpServletRequest req) {
        req.getSession().invalidate();

//        resp.sendRedirect("/hotel/index.jspx");
        return "index";
    }
}
