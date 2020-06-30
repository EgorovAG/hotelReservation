package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Role;
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
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class RegistrationUsersController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationUsersController.class);

    private AuthUserService authUserService;
    private ClientService clientService;

    public RegistrationUsersController(AuthUserService authUserService, ClientService clientService) {
        this.authUserService = authUserService;
        this.clientService = clientService;
    }


    @PostMapping("/deleteUsers")
    public String doPost(HttpServletRequest req) {


        int id = Integer.parseInt(req.getParameter("id"));
        clientService.deleteAuthUserAndClientByClientId(id);
        return "redirect:/paginationRegistratedUsers";
    }

    @PostMapping("/registration")
    public String doPost(HttpServletRequest req, @Valid AuthUser authUser, @Valid Client client) {

        if (!authUserService.checkLogin(authUser.getLogin())) {
            authUser.setRole(Role.USER);
            authUser = authUserService.saveAuthUserAndClient(authUser, client);
            req.getSession().setAttribute("authUser", authUser);
            client = new Client(authUser.getClient().getId(), client.getFirstName(), client.getSecondName(),
                    client.getEmail(), client.getPhone());
            req.getSession().setAttribute("client", client);
            Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "personalArea";
        } else {
            req.setAttribute("errorUser", "Пользователь с таким именем уже существует");
            return "registration";
        }
    }

    private List<GrantedAuthority> getAuthorities() {
        return Arrays.asList((GrantedAuthority) () -> "ROLE_USER");
    }

    @GetMapping("/toRegistrationJspx")
    public String doGet() {
        return "registration";
    }
}
