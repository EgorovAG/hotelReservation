package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class ToPayOrderController {
    private static final Logger log = LoggerFactory.getLogger(ToPayOrderController.class);
    private OrderService orderService;

    public ToPayOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/toPayOrder")
    public String doPost(@RequestParam(value = "orderId") int orderId,
                         @RequestParam(value = "condition") String condition,
                         @RequestParam(value = "price", defaultValue = "0") int price,
                         Model model, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        int clientId = client.getId();

        List<OrderForClientDTO> orderForClients;
        List<OrderClient> orderClients;
        AuthUser authUser;
        if (condition.equals("DELETE")) {
            if (orderService.checkIdOrderByClientOrder(orderId, clientId)) {
                orderService.deleteOrderByOrderId(orderId);
                authUser = (AuthUser) session.getAttribute("authUser");
                orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
                orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
                model.addAttribute("orderForClients", orderForClients);
                model.addAttribute("orderClients", orderClients);
                model.addAttribute("error", "error.orderDeleted");
                return "orderListForClient";
            } else {
                model.addAttribute("error", "error.noOrderWithId");
                orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
                model.addAttribute("orderForClients", orderForClients);

                return "orderListForClient";
            }

        } else {
            Condition cond = orderService.readConditionByOrderId(orderId);
            authUser = (AuthUser) session.getAttribute("authUser");
            orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
            model.addAttribute("orderForClients", orderForClients);
            orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
            model.addAttribute("orderClients", orderClients);

            switch (cond) {
                case CONSIDERATION:
                    model.addAttribute("error", "error.consideration");
                    return "orderListForClient";
                case APPROVED:
                    session.setAttribute("price", price);
                    session.setAttribute("orderId", orderId);
                    return "orderPayment";
                case REJECTED:
                    model.addAttribute("error", "error.rejected");
                    return "orderListForClient";
                default:
                    model.addAttribute("error", "error.alreadyPaid");
                    return "orderListForClient";
            }
        }
    }
}
