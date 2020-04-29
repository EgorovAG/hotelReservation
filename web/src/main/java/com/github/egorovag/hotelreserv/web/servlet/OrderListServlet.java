package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.Condition;
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
    private OrderService iorderService;
    private List<OrderForAdmin> orderWithClients;

    @Override
    public void init() throws ServletException {
        iorderService = DefaultOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderForAdmin> orderWithClients = iorderService.readOrderListService();
        if(orderWithClients==null || orderWithClients.isEmpty()){
            req.setAttribute("orderWithClients",null);
        } else {
            req.setAttribute("orderWithClients", orderWithClients);
        }
        req.getRequestDispatcher("/orderList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String cond = req.getParameter("condition");
        if(cond.equals("DELETE")){
            iorderService.deleteOrderByOrderId(orderId);

        } else {
            Condition condition = Condition.valueOf(cond);
            if(iorderService.readConditionByOrderId(orderId)==Condition.PAID){
                req.setAttribute("error", "Заказ уже был оплачен после одобрения");
            } else {
                iorderService.updateOrderList(orderId, condition);
            }
        }
        orderWithClients = iorderService.readOrderListService();
        req.setAttribute("orderWithClients", orderWithClients);
        req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
    }
}
