package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class RegistratedUsersController {

    private static final Logger log = LoggerFactory.getLogger(RegistratedUsersController.class);

    private AuthUserService authUserService;
    private ClientService clientService;
//    private BlackListUsersService blackListUsersService;
//    private OrderService orderService;


    public RegistratedUsersController(AuthUserService authUserService, ClientService clientService) {
        this.authUserService = authUserService;
        this.clientService = clientService;
    }

//    // Есть пагинация, теперь это ВСЕ не надо
//    @GetMapping("/registratedUsers")
//    public String doGet(HttpServletRequest req){
//
//        List<AuthUserWithClientDTO> authUserWithClients = authUserService.readListAuthUserWithClientDTO();
//        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
//        return "forward:/registratedUsers.jsp";
//    }

    @PostMapping("/registratedUsers")
    public String doPost(HttpServletRequest req) {


        int id = Integer.parseInt(req.getParameter("id"));
//        orderService.deleteOrderByClientId(id); уже не надо с 1к1
//        blackListUsersService.deleteBlackListUserById(id); уже не надо с 1к1
        clientService.deleteAuthUserAndClientByClientId(id);
//        сlientService.deleteClientById(id); // из-за hiber уже не надо
//        checkUserService.deleteUserById(id);  // из-за hiber уже не надо
//        List<AuthUserWithClient> authUserWithClients = checkUserService.readListAuthUserWithClient();
//        req.getSession().setAttribute("authUserWithClients", authUserWithClients);
//        req.getRequestDispatcher("/paginationRegistratedUsers").forward(req,resp);
        return "redirect:/paginationRegistratedUsers";
//        resp.sendRedirect("/hotel/paginationRegistratedUsers");
    }
}
