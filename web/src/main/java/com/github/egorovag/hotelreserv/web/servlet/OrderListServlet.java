package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {
    private OrderService orderService;
    private List<OrderForAdmin> orderForAdmins;

    @Override
    public void init() throws ServletException {
        orderService = DefaultOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderForAdmin> orderForAdmins = orderService.readOrderListService();
        if(orderForAdmins==null || orderForAdmins.isEmpty()){
            req.setAttribute("orderForAdmins",null);
        } else {
            req.setAttribute("orderForAdmins", orderForAdmins);
        }
        req.getRequestDispatcher("/orderList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        orderForAdmins = orderService.readOrderListService();
        req.setAttribute("orderForAdmins", orderForAdmins);
        req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
    }
}
