package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.enums.Role;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webUtils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/registration")
public class RegistrationServlet {

    private static final Logger log = LoggerFactory.getLogger(RegisteredUsersServlet.class);

//    private AuthUserService userService;
    private ClientService clientService;

    public RegistrationServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

//        AuthUser authUser = userService.saveAuthUser(login, password, Role.USER);
//        Client client = new Client(firstName, secondName, email, phone, authUser.getId());

//        oneToOne
        AuthUser authUser = new AuthUser(login, password, Role.USER);
        Client client = new Client(null,firstName, secondName, email, phone, authUser);
        authUser = clientService.saveAuthUserAndClient(authUser, client);
        req.getSession().setAttribute("authUser", authUser);
        client = new Client(authUser.getClient().getId(), firstName, secondName, email, phone, authUser);

//        clientService.saveClient(client);
//        req.getSession().setAttribute("authUser", authUser);
        req.getSession().setAttribute("client", client);
        return "personalArea.jsp";
    }
}
