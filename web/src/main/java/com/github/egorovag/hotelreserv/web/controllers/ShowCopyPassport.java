package com.github.egorovag.hotelreserv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class ShowCopyPassport {

    @GetMapping("/showCopyPass")
    public String showPass(@RequestParam(value = "login") String login, Model model) {
        model.addAttribute("login", login);
        return "showCopyPass";
    }
}
