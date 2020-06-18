package com.github.egorovag.hotelreserv.web.controllers;


import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
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
public class BlackListController {
    private static final Logger log = LoggerFactory.getLogger(BlackListController.class);
    private final BlackListService blackListService;

    public BlackListController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @GetMapping("/blackListUsers")
    public String get(ModelMap map) {
        List<BlackListUsersDTO> blackListUsers = blackListService.readBlackListUsersDTO();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            map.put("blackListUsers", null);
        } else {
            map.put("blackListUsers", blackListUsers);
        }
        return "blackListUsers";
    }

    @PostMapping("/blackListUsers")
    public String delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        log.info("blackList with Client id:{} deleted", id);
        blackListService.deleteBlackListById(id);
        return "redirect:/blackListUsers";
    }

    @PostMapping("/blockUser")
    public String check(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (blackListService.checkBlackListByUserId(id)) {
            req.setAttribute("error", "Такой пользователь уже заблокирован!");
            return "registratedUsers";
        } else {
            blackListService.saveBlackListByAuthUserId(id);
        }
        req.setAttribute("error", "Выбранный пользователь заблокирован!");
        return "registratedUsers";
    }

    @GetMapping("/toPersonalAreaJspx")
    public String doGet(){
        return "personalArea";
    }

}

