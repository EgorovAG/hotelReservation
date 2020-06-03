package com.github.egorovag.hotelreserv.web.servlet;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/login")
public class LoginServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private final AuthUserService authUserService;
    private final BlackListUsersService blackListUsersService;

    public LoginServlet(AuthUserService authUserService, BlackListUsersService blackListUsersService) {
        this.authUserService = authUserService;
        this.blackListUsersService = blackListUsersService;
    }

    @GetMapping
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        return "personalArea.jsp";
    }

    @PostMapping
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser authUser = authUserService.checkUser(login, password);
        if (authUser == null) {
            req.setAttribute("error", "Вы ввели неверное имя или пароль либо Вам необходимо зарегистрироваться");
            return "login.jsp";
        } else {
            if (authUser.getLogin().equals("admin")) {
                req.getSession().setAttribute("authUser", authUser);
                return "/personalArea.jsp";
            } else {
                int id = authUser.getId();
                if (blackListUsersService.checkBlackUserByUserId(id)) {
                    return "/youAreBlockClient.jsp";
                } else {
                    req.getSession().setAttribute("authUser", authUser);
                    Client client = authUserService.readClientByAuthUserId(authUser.getId());
                    if (client != null) {
                        req.getSession().setAttribute("client", client);
                    }
                    return "/personalArea.jsp";
                }
            }
        }
    }
}


