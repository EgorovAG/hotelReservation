package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.СlientService;
import com.github.egorovag.hotelreserv.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registratedUsers")
public class RegisteredUsersServlet extends HttpServlet {
    private AuthUserService checkUserService;
    private СlientService clientService;
    private BlackListUsersService blackListUsersService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        checkUserService = DefaultAuthUserService.getInstance();
        clientService = DefaultClientService.getInstance();
        blackListUsersService = DefaultBlackListUsersService.getInstance();
        orderService = DefaultOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AuthUserWithClient> authUserWithClients = checkUserService.readListClient();
        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
//        orderService.deleteOrderByClientId(id); уже не надо с 1к1
//        blackListUsersService.deleteBlackListUserById(id); уже не надо с 1к1
        clientService.deleteAuthUserAndClientByUserIdDao(id);
//        сlientService.deleteClientById(id);
//        checkUserService.deleteUserById(id);
        List<AuthUserWithClient> authUserWithClients = checkUserService.readListClient();
        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
    }
}
