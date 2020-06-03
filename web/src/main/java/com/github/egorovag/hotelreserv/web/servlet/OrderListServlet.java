package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
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
@RequestMapping("/orderList")
public class OrderListServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private final OrderService orderService;

    public OrderListServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderForAdmin> orderForAdmins = orderService.readOrderList();
        if(orderForAdmins==null || orderForAdmins.isEmpty()){
            req.setAttribute("orderForAdmins",null);
        } else {
            req.setAttribute("orderForAdmins", orderForAdmins);
        }
        return "/orderList.jsp";
    }

    @PostMapping
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        List<OrderForAdmin> orderForAdmins = orderService.readOrderList();
        req.setAttribute("orderForAdmins", orderForAdmins);
        return "/orderList.jsp";
    }
}
