package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AuthUserService checkUserService;
    private BlackListUsersService blackListUsersService;

    @Override
    public void init() {
        checkUserService = DefaultAuthUserService.getInstance();
        blackListUsersService = DefaultBlackListUsersService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        req.getRequestDispatcher("personalArea.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser authUser = checkUserService.checkUser(login, password);
        if (authUser == null) {
            req.setAttribute("error", "Вы ввели неверное имя или пароль либо Вам необходимо зарегистрироваться");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (authUser.getLogin().equals("admin")) {
                req.getSession().setAttribute("authUser", authUser);
                req.getRequestDispatcher("/personalArea.jsp").forward(req, resp);
            } else {
                int id = authUser.getId();
                if (blackListUsersService.checkBlackUserByUserId(id)) {
                    req.getRequestDispatcher("/youAreBlockClient.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("authUser", authUser);
                    Client client = checkUserService.readClientByAuthUserId(authUser.getId());
                    if (client != null) {
                        req.getSession().setAttribute("client", client);
                    }
                    req.getRequestDispatcher("/personalArea.jsp").forward(req, resp);
                }
            }
        }
    }
}


