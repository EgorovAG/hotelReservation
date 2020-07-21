package com.github.egorovag.hotelreserv.web.controllers;


import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import com.github.egorovag.hotelreserv.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String get(Model model) {
        List<BlackListUsersDTO> blackListUsers = blackListService.readBlackListUsersDTO();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            model.addAttribute("blackListUsers", null);
        } else {
            model.addAttribute("blackListUsers", blackListUsers);
        }
        return "blackListUsers";
    }

    @PostMapping("/blackListUsers")
    public String delete(@RequestParam(value = "id") int id) {
        log.info("blackList with Client id:{} deleted", id);
        blackListService.deleteBlackListById(id);
        return "redirect:/blackListUsers";
    }

    @PostMapping("/blockUser")
    public String check(@RequestParam(value = "id") int id, Model model) {
        if (blackListService.checkBlackListByUserId(id)) {
            model.addAttribute("error", "error.locked");
            return "registeredUsers";
        } else {
            blackListService.saveBlackListByAuthUserId(id);
        }
        model.addAttribute("error", "error.userBlocked");
        return "registeredUsers";
    }

    @GetMapping("/toPersonalAreaJspx")
    public String doGet(){
        return "personalArea";
    }

}

