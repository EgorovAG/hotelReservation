package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private final AuthUserService authUserService;
    private final BlackListUsersService blackListUsersService;

    public LoginServlet(AuthUserService authUserService, BlackListUsersService blackListUsersService) {
        this.authUserService = authUserService;
        this.blackListUsersService = blackListUsersService;
    }

    @GetMapping("/login")
    public String doGet(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            return "forward:/login.jsp";
        }
        return "forward:/personalArea.jsp";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser authUser = authUserService.checkUser(login, password);
        if (authUser == null) {
            req.setAttribute("error", "Вы ввели неверное имя или пароль либо Вам необходимо зарегистрироваться");
            return "forward:/login.jsp";
        } else {
            if (authUser.getLogin().equals("admin")) {
                req.getSession().setAttribute("authUser", authUser);
                return "forward:/personalArea.jsp";
            } else {
                int id = authUser.getId();
                if (blackListUsersService.checkBlackUserByUserId(id)) {
                    return "forward:/youAreBlockClient.jsp";
                } else {
                    req.getSession().setAttribute("authUser", authUser);
                    Client client = authUserService.readClientByAuthUserId(authUser.getId());
                    if (client != null) {
                        req.getSession().setAttribute("client", client);
                    }
                    return "forward:/personalArea.jsp";
                }
            }
        }
    }
}


