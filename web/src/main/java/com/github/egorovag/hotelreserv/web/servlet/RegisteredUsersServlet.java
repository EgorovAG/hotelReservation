package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
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
    private ClientService clientService;
    private BlackListUsersService blackListUsersService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        checkUserService = DefaultAuthUserService.getInstance();
        clientService = DefaultClientService.getInstance();
        blackListUsersService = DefaultBlackListUsersService.getInstance();
        orderService = DefaultOrderService.getInstance();
    }


    // Есть пагинация, теперь это ВСЕ не надо
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AuthUserWithClient> authUserWithClients = checkUserService.readListAuthUserWithClient();
        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id = Integer.parseInt(req.getParameter("id"));
//        orderService.deleteOrderByClientId(id); уже не надо с 1к1
//        blackListUsersService.deleteBlackListUserById(id); уже не надо с 1к1
        clientService.deleteAuthUserAndClientByUserId(id);
//        сlientService.deleteClientById(id); // из-за hiber уже не надо
//        checkUserService.deleteUserById(id);  // из-за hiber уже не надо
//        List<AuthUserWithClient> authUserWithClients = checkUserService.readListAuthUserWithClient();
//        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
//        req.getRequestDispatcher("/paginationRegistratedUsers").forward(req,resp);
        resp.sendRedirect("/hotel/paginationRegistratedUsers");
    }
}
