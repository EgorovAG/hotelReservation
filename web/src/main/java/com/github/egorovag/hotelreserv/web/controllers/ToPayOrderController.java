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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class ToPayOrderController {
    private static final Logger log = LoggerFactory.getLogger(ToPayOrderController.class);
    private OrderService orderService;

    public ToPayOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping("/toPayOrder")
//    public String doGet(HttpServletRequest req) {
////        !!!!!!!!!!!!!!!!!
////        Client client = (Client) req.getSession().getAttribute("client");
//        return "toPayOrder";
//    }

    @PostMapping("/toPayOrder")
    public String doPost(HttpServletRequest req) {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String condition = req.getParameter("condition");
        Client client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getId();

        List<OrderForClientDTO> orderForClients;
        List<OrderClient> orderClients;
        AuthUser authUser;
        if (condition.equals("DELETE")) {
            if (orderService.checkIdOrderByClientOrder(orderId, clientId)) {
                orderService.deleteOrderByOrderId(orderId);
                authUser = (AuthUser) req.getSession().getAttribute("authUser");
                orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
                orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
                req.setAttribute("orderForClients", orderForClients);
                req.setAttribute("orderClients", orderClients);
                req.setAttribute("Error", "Заказ удален!");
                return "orderListForClient";
            } else {
                req.setAttribute("Error", "Заказа с таким id у Вас нет!");
                orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
                req.setAttribute("orderForClients", orderForClients);

                return "orderListForClient";
            }

        } else {
            Condition cond = orderService.readConditionByOrderId(orderId);
            authUser = (AuthUser) req.getSession().getAttribute("authUser");
            orderForClients = orderService.readOrderForClientDTOByClientId(clientId);
            req.setAttribute("orderForClients", orderForClients);
            orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
            req.setAttribute("orderClients", orderClients);

            switch (cond) {
                case CONSIDERATION:
                    req.setAttribute("Error", "Заказ еще не одобрен администратором");
                    return "orderListForClient";
                case APPROVED:
//                    int price = orderService.readPriceForRoomByOrderId(orderId);
                    int price = Integer.parseInt(req.getParameter("price"));
                    req.getSession().setAttribute("price", price);
                    req.getSession().setAttribute("orderId", orderId);
                    return "orderPayment";
                case REJECTED:
                    req.setAttribute("Error", "Ваш заказ отклонен администратором!");
                    return "orderListForClient";
                default:
                    req.setAttribute("Error", "Заказ уже оплачен, ждем Вас в нашей гостинице!");
                    return "orderListForClient";
            }
        }
    }
}
