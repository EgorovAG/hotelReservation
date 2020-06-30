package com.github.egorovag.hotelreserv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class ShowCopyPassport {

    @GetMapping("/showCopyPass")
    public String showPass(HttpServletRequest request) {
        request.setAttribute("login", request.getParameter("login"));
        return "showCopyPass";
    }
}
