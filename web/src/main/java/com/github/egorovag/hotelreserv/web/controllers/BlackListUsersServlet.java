package com.github.egorovag.hotelreserv.web.controllers;


import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class BlackListUsersServlet {
    private static final Logger log = LoggerFactory.getLogger(BlackListUsersServlet.class);
    private final BlackListUsersService blackListUsersService;

    public BlackListUsersServlet(BlackListUsersService blackListUsersService) {
        this.blackListUsersService = blackListUsersService;
    }

    @GetMapping("/blackListUsers")
    public String doGet(HttpServletRequest req) {
        List<BlackListUsers> blackListUsers = blackListUsersService.readBlackListUsersLists();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        return "forward:/blackListUsers.jsp";
    }

    @PostMapping("/blackListUsers")
    public String doPost(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        blackListUsersService.deleteBlackListUserById(id);
        List<BlackListUsers> blackListUsers = blackListUsersService.readBlackListUsersLists();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        return "forward:/blackListUsers.jsp";
    }
}

