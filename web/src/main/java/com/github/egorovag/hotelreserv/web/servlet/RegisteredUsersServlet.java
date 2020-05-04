package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultCheckUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.CheckUserService;
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
    private CheckUserService checkUserService;
    private СlientService сlientService;
    private BlackListUsersService blackListUsersService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        checkUserService = DefaultCheckUserService.getInstance();
        сlientService = DefaultClientService.getInstance();
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
        orderService.deleteOrderByClientId(id);
        blackListUsersService.deleteBlackListUserById(id);
        сlientService.deleteClientSeviceById(id);
        checkUserService.deleteUserByIdService(id);
        List<AuthUserWithClient> authUserWithClients = checkUserService.readListClient();
        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
    }
}
