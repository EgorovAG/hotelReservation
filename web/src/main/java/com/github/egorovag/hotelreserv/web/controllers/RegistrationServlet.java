package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class RegistrationServlet {

    private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    private AuthUserService authUserService;

    public RegistrationServlet(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/registration")
    public String doPost(HttpServletRequest req) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (!authUserService.checkLogin(login)) {
            AuthUser authUser = new AuthUser(login, password, Role.USER);
            Client client = new Client(null, firstName, secondName, email, phone);
            authUser = authUserService.saveAuthUserAndClient(authUser, client);
            req.getSession().setAttribute("authUser", authUser);
            client = new Client(authUser.getClient().getId(), firstName, secondName, email, phone);
            req.getSession().setAttribute("client", client);
            return "personalArea";
        } else {
            req.setAttribute("errorUser", "Пользователь с таким именем уже существует");
            return "registration";
        }
    }
}
