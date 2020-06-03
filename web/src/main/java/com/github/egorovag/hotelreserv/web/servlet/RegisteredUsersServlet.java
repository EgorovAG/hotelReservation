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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webUtils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/registratedUsers")
public class RegisteredUsersServlet {

    private static final Logger log = LoggerFactory.getLogger(RegisteredUsersServlet.class);

    private AuthUserService checkUserService;
    private ClientService clientService;
//    private BlackListUsersService blackListUsersService;
//    private OrderService orderService;


    public RegisteredUsersServlet(AuthUserService checkUserService, ClientService clientService) {
        this.checkUserService = checkUserService;
        this.clientService = clientService;
    }

    // Есть пагинация, теперь это ВСЕ не надо
    @GetMapping
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AuthUserWithClient> authUserWithClients = checkUserService.readListAuthUserWithClient();
        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
        return "/registratedUsers.jsp";
    }

    @PostMapping
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id = Integer.parseInt(req.getParameter("id"));
//        orderService.deleteOrderByClientId(id); уже не надо с 1к1
//        blackListUsersService.deleteBlackListUserById(id); уже не надо с 1к1
        clientService.deleteAuthUserAndClientByUserId(id);
//        сlientService.deleteClientById(id); // из-за hiber уже не надо
//        checkUserService.deleteUserById(id);  // из-за hiber уже не надо
//        List<AuthUserWithClient> authUserWithClients = checkUserService.readListAuthUserWithClient();
//        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
//        req.getRequestDispatcher("/paginationRegistratedUsers").forward(req,resp);
        return "redirect:/hotel/paginationRegistratedUsers";
//        resp.sendRedirect("/hotel/paginationRegistratedUsers");
    }
}
