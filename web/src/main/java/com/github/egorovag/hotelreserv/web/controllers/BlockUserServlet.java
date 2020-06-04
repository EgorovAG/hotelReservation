package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webUtils.WebUtils;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping
public class BlockUserServlet {
    private static final Logger log = LoggerFactory.getLogger(BlockUserServlet.class);
    private final BlackListUsersService blackListUsersService;

    public BlockUserServlet(BlackListUsersService blackListUsersService) {
        this.blackListUsersService = blackListUsersService;
    }

    @PostMapping("/blockUser")
    public String doPost(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (blackListUsersService.checkBlackUserByUserId(id)) {
            req.setAttribute("error", "Такой пользователь уже заблокирован!");
            return "forward:/registratedUsers.jsp";
        } else {
            blackListUsersService.saveBlackListUserById(id);
        }
        req.setAttribute("error", "Выбранный пользователь заблокирован!");
        return "forward:/registratedUsers.jsp";
    }
}
