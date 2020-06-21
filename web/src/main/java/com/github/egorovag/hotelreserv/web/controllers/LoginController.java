package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.BlackListService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final AuthUserService authUserService;
    private final BlackListService blackListUsersService;
    private final ClientService clientService;
    private AuthUser authUser;

    public LoginController(AuthUserService authUserService, BlackListService blackListUsersService, ClientService clientService) {
        this.authUserService = authUserService;
        this.blackListUsersService = blackListUsersService;
        this.clientService = clientService;
    }

    @GetMapping("/login")
    public String doGet(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            return "login";
        }
        return "personalArea";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        authUser = authUserService.checkUser(login, password);
        if (authUser == null) {
            req.setAttribute("error", "Вы ввели неверное имя или пароль либо Вам необходимо зарегистрироваться");
            return "login";
        } else {
            if (authUser.getLogin().equals("admin")) {
                req.getSession().setAttribute("authUser", authUser);
                log.info("user {} logged", authUser.getLogin());
                Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "personalArea";
            } else {
                int id = authUser.getId();
                if (blackListUsersService.checkBlackListByUserId(id)) {
                    log.info("user {} logged", authUser.getLogin());
                    return "youAreBlockClient";
                } else {
                    req.getSession().setAttribute("authUser", authUser);
                    Client client = clientService.readClientByClientId(authUser.getClient().getId());
                    if (client != null) {
                        req.getSession().setAttribute("client", client);
                    }
                    log.info("user {} logged", authUser.getLogin());
                    Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    return "personalArea";
                }
            }
        }
    }

    private List<GrantedAuthority> getAuthorities() {
        if (authUser.getLogin().equals("admin")) {
            return Arrays.asList((GrantedAuthority) () -> "ROLE_ADMIN");
        } else {
            return Arrays.asList((GrantedAuthority) () -> "ROLE_USER");
        }
    }
}


