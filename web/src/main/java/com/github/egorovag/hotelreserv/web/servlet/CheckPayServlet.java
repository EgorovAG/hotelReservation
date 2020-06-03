package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webUtils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/checkPay")
public class CheckPayServlet {
    private static final Logger log = LoggerFactory.getLogger(CheckPayServlet.class);
    private final OrderService orderService;

    public CheckPayServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int price = (int) req.getSession().getAttribute("price");
        int sum = Integer.parseInt(req.getParameter("sum"));


        if(sum == price) {
            int orderId= (int) req.getSession().getAttribute("orderId");
            orderService.updateOrderList(orderId, Condition.PAID);
            return"/okPay.jsp";

        } else {
            req.setAttribute("Error","Вы ввели неверную сумму, попробуйте еще раз");
            return "/toPay.jsp";
        }
    }
}
