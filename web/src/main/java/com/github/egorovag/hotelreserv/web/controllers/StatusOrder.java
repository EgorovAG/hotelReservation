package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class StatusOrder {
    private static final Logger log = LoggerFactory.getLogger(StatusOrder.class);
    private OrderService orderService;

    public StatusOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/statusOrder")
    public String doGet(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        List<OrderForClient> orderForClients = orderService.readOrderForClientByClientId(authUser.getClient().getId());
        List<OrderClient> orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
        if (orderForClients == null || orderForClients.isEmpty()) {
            req.setAttribute("orderForClients", null);
        } else {
            req.setAttribute("orderForClients", orderForClients);
            req.setAttribute("orderClients", orderClients);
        }
        return "statusOrderNEW";

//        } else {
//            req.setAttribute("orderList", orderList);
//            Room room = iroomService.readRoomByIdService(order.getRoomId());
//            req.setAttribute("room", room);
//            req.getRequestDispatcher("/statusOrder.jsp").forward(req, resp);
//        }
    }
}
