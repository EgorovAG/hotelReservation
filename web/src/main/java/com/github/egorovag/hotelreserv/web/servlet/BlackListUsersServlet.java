package com.github.egorovag.hotelreserv.web.servlet;


import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webUtils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/blackListUsers")
public class BlackListUsersServlet {
    private static final Logger log = LoggerFactory.getLogger(BlackListUsersServlet.class);
    private final BlackListUsersService blackListUsersService;

    public BlackListUsersServlet(BlackListUsersService blackListUsersService) {
        this.blackListUsersService = blackListUsersService;
    }

    @GetMapping
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BlackListUsers> blackListUsers = blackListUsersService.readBlackListUsersLists();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        return "/blackListUsers.jsp";
    }

    @PostMapping
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        blackListUsersService.deleteBlackListUserById(id);
        List<BlackListUsers> blackListUsers = blackListUsersService.readBlackListUsersLists();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        return "/blackListUsers.jsp";
    }
}

