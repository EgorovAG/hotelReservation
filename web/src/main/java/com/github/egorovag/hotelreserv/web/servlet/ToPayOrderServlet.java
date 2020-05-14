package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
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

@WebServlet("/toPayOrder")
public class ToPayOrderServlet extends HttpServlet {
    private OrderService orderService;
    private List<OrderForClient> orderForClients;

    @Override
    public void init() throws ServletException {
        orderService = DefaultOrderService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        !!!!!!!!!!!!!!!!!
//        Client client = (Client) req.getSession().getAttribute("client");
        req.getRequestDispatcher("/toPayOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String condition = req.getParameter("condition");
//        Condition condition = Condition.valueOf(req.getParameter("condition"));
        Client client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getId();

        if (condition.equals("DELETE")){
            if(orderService.checkIdOrderByClientOrder(orderId, clientId)){
                orderService.deleteOrderByOrderId(orderId);
                orderForClients = orderService.readOrderForClientByClientId(clientId);
                req.setAttribute("orderForClients", orderForClients);
                req.setAttribute("Error", "Заказ удален!");
                req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req,resp);
            } else {
                req.setAttribute("Error", "Заказа с таким id у Вас нет!");
                orderForClients = orderService.readOrderForClientByClientId(clientId);
                req.setAttribute("orderForClients", orderForClients);


                req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req,resp);
            }
//            req.getSession().setAttribute("orderId", orderId);
//            int price = iorderService.readPriceByOrderId(orderId);
//            req.setAttribute("price", price);
//            req.getRequestDispatcher("/toPay.jsp").forward(req,resp);
        } else {
            Condition cond = orderService.readConditionByOrderId(orderId);
            switch (cond){
                case CONSIDERATION:
                    req.setAttribute("Error", "Заказ еще не одобрен администратором");
                    orderForClients = orderService.readOrderForClientByClientId(clientId);
                    req.setAttribute("orderForClients", orderForClients);
                    req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req,resp);
                    break;
                case APPROVED:
                    int price = orderService.readPriceForRoomByOrderId(orderId);
                    req.getSession().setAttribute("price", price);
                    req.getSession().setAttribute("orderId", orderId);
                    req.getRequestDispatcher("/toPay.jsp").forward(req,resp);
                    break;
                case REJECTED:
                    req.setAttribute("Error", "Ваш заказ отклонен администратором!");
                    orderForClients = orderService.readOrderForClientByClientId(clientId);
                    req.setAttribute("orderForClients", orderForClients);
                    req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req,resp);
                    break;
                default:
                    req.setAttribute("Error", "Заказ уже оплачен, ждем Вас в нашей гостинице!");
                    orderForClients = orderService.readOrderForClientByClientId(clientId);
                    req.setAttribute("orderForClients", orderForClients);
                    req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req,resp);



            }
        }
    }
}
