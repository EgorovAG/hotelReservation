package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
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
    private CheckUserService icheckUserService;
    private СlientService iclientService;
    private BlackListUsersService iblackListUsersService;
    private OrderService iorderService;

    @Override
    public void init() throws ServletException {
        icheckUserService = DefaultCheckUserService.getInstance();
        iclientService = DefaultClientService.getInstance();
        iblackListUsersService = DefaultBlackListUsersService.getInstance();
        iorderService = DefaultOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AuthUserWithClient> authUserList = icheckUserService.readListClient();
        req.getSession().setAttribute("authUserList", authUserList);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        iorderService.deleteOrderByClientId(id);
        iblackListUsersService.deleteBlackListUserById(id);
        iclientService.deleteClientSeviceById(id);
        icheckUserService.deleteUserByIdService(id);
        List<AuthUserWithClient> authUserList = icheckUserService.readListClient();
        req.getSession().setAttribute("authUserList", authUserList);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
    }
}
