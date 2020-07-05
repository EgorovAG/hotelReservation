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

//    @GetMapping("/toPayOrder")
//    public String doGet(HttpServletRequest req) {
////        !!!!!!!!!!!!!!!!!
////        Client client = (Client) req.getSession().getAttribute("client");
//        return "toPayOrder";
//    }

    @PostMapping("/toPayOrder")
    public String doPost(@RequestParam(value = "orderId") int orderId,
                         @RequestParam(value = "condition") String condition,
                         @RequestParam(value = "price", defaultValue = "0") int price,
                         Model model, HttpSession session) {
//        int orderId = Integer.parseInt(req.getParameter("orderId"));
//        String condition = req.getParameter("condition");
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
                model.addAttribute("Error", "Заказ удален!");
                return "orderListForClient";
            } else {
                model.addAttribute("Error", "Заказа с таким id у Вас нет!");
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
                    model.addAttribute("Error", "Заказ еще не одобрен администратором");
                    return "orderListForClient";
                case APPROVED:
//                    int price = orderService.readPriceForRoomByOrderId(orderId);
//                    int price = Integer.parseInt(price);
                    session.setAttribute("price", price);
                    session.setAttribute("orderId", orderId);
                    return "orderPayment";
                case REJECTED:
                    model.addAttribute("Error", "Ваш заказ отклонен администратором!");
                    return "orderListForClient";
                default:
                    model.addAttribute("Error", "Заказ уже оплачен, ждем Вас в нашей гостинице!");
                    return "orderListForClient";
            }
        }
    }
}
