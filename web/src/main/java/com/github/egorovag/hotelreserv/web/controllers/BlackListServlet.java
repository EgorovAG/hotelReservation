package com.github.egorovag.hotelreserv.web.controllers;


import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import com.github.egorovag.hotelreserv.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class BlackListServlet {
    private static final Logger log = LoggerFactory.getLogger(BlackListServlet.class);
    private final BlackListService blackListService;

    public BlackListServlet(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @GetMapping("/blackListUsers")
    public String doGet(ModelMap map) {
        List<BlackListUsers> blackListUsers = blackListService.readBlackListUsersLists();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            map.put("blackListUsers", null);
        } else {
            map.put("blackListUsers", blackListUsers);
        }
        return "blackListUsers";
    }

    @PostMapping("/blackListUsers")
    public String doPost(HttpServletRequest req, ModelMap map) {
        int id = Integer.parseInt(req.getParameter("id"));
        blackListService.deleteBlackListById(id);
        return "redirect:/blackListUsers";
    }

    @GetMapping("/toPersonalAreaJspx")
    public String doGet(){
        return "personalArea.jspx";
    }

}

