package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.dao.api.IOrderDao;
import com.github.egorovag.hotelreserv.model.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.CheckUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import com.github.egorovag.hotelreserv.service.OrderService;
import com.github.egorovag.hotelreserv.service.api.IblackListUsersService;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;
import com.github.egorovag.hotelreserv.service.api.IclientService;
import com.github.egorovag.hotelreserv.service.api.IorderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registratedUsers")
public class RegisteredUsersServlet extends HttpServlet {
    private IcheckUserService icheckUserService;
    private IclientService iclientService;
    private IblackListUsersService iblackListUsersService;
    private IorderService iorderService;

    @Override
    public void init() throws ServletException {
        icheckUserService = CheckUserService.getInstance();
        iclientService = ClientService.getInstance();
        iblackListUsersService = BlackListUsersService.getInstance();
        iorderService = OrderService.getInstance();
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
