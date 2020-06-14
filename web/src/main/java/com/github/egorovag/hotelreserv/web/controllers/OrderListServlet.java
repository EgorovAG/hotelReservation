package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.OrderService;
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
public class OrderListServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private final OrderService orderService;

    public OrderListServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderList")
    public String doGet(HttpServletRequest req) {
        List<OrderForAdmin> orderForAdmins = orderService.readOrderListForAdmin();
        List<OrderClient> orderClients = orderService.readOrderClientList();
        if(orderForAdmins==null || orderForAdmins.isEmpty()){
            req.setAttribute("orderForAdmins",null);
        } else {
            req.setAttribute("orderForAdmins", orderForAdmins);
            req.setAttribute("orderClients", orderClients);
        }
        return "orderList";
    }

    @PostMapping("/orderList")
    public String doPost(HttpServletRequest req) {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String cond = req.getParameter("condition");
        if(cond.equals("DELETE")){
            orderService.deleteOrderByOrderId(orderId);
        } else {
            Condition condition = Condition.valueOf(cond);
            if(orderService.readConditionByOrderId(orderId)==Condition.PAID){
                req.setAttribute("error", "Заказ уже был оплачен после одобрения");
            } else {
                orderService.updateOrderList(orderId, condition);
            }
        }
        List<OrderForAdmin> orderForAdmins = orderService.readOrderListForAdmin();
        List<OrderClient> orderClients = orderService.readOrderClientList();
        req.setAttribute("orderForAdmins", orderForAdmins);
        req.setAttribute("orderClients", orderClients);
        return "orderList";
    }
}
