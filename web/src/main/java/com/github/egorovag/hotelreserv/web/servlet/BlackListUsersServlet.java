package com.github.egorovag.hotelreserv.web.servlet;


import com.github.egorovag.hotelreserv.model.BlackListUsers;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/blackListUsers")
public class BlackListUsersServlet extends HttpServlet {
    BlackListUsersService iblackListUsersService;

    @Override
    public void init() throws ServletException {
        iblackListUsersService = DefaultBlackListUsersService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BlackListUsers> blackListUsers = iblackListUsersService.readBlackListUsersListsService();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        req.getRequestDispatcher("/blackListUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iblackListUsersService.deleteBlackListUserById(id);
        List<BlackListUsers> blackListUsers = iblackListUsersService.readBlackListUsersListsService();
        if (blackListUsers == null || blackListUsers.isEmpty()) {
            req.setAttribute("blackListUsers", null);
        } else {
            req.setAttribute("blackListUsers", blackListUsers);
        }
        req.getRequestDispatcher("/blackListUsers.jsp").forward(req, resp);
    }
}

