package com.github.egorovag.hotelreserv.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping
public class LogoutServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @GetMapping("/logout")
    public String doGet(HttpServletRequest req) {
        req.getSession().invalidate();

//        resp.sendRedirect("/hotel/index.jsp");
        return "redirect:/index.jsp";
    }
}
