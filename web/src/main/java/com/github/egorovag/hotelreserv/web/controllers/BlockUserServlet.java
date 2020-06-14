package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class BlockUserServlet {
    private static final Logger log = LoggerFactory.getLogger(BlockUserServlet.class);
    private final BlackListService blackListUsersService;

    public BlockUserServlet(BlackListService blackListUsersService) {
        this.blackListUsersService = blackListUsersService;
    }

    @PostMapping("/blockUser")
    public String doPost(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (blackListUsersService.checkBlackListByUserId(id)) {
            req.setAttribute("error", "Такой пользователь уже заблокирован!");
            return "registratedUsers";
        } else {
            blackListUsersService.saveBlackListByAuthUserId(id);
        }
        req.setAttribute("error", "Выбранный пользователь заблокирован!");
        return "registratedUsers";
    }
}
