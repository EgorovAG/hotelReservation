package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.impl.DefaultRoomService;
import com.github.egorovag.hotelreserv.service.OrderService;
import com.github.egorovag.hotelreserv.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/statusOrder")
public class StatusOrder extends HttpServlet {
    private OrderService iorderService;
    private RoomService iroomService;

    @Override
    public void init() {
        iorderService = DefaultOrderService.getInstance();
        iroomService = DefaultRoomService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        List<OrderForClient> orderForClients = iorderService.readOrderForClientByClient_Id(authUser.getId());
        if (orderForClients == null || orderForClients.isEmpty()) {
            req.setAttribute("orderForClients", null);
        } else {
            req.setAttribute("orderForClients", orderForClients);
        }
        req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("orderList", orderList);
//            Room room = iroomService.readRoomByIdService(order.getRoomId());
//            req.setAttribute("room", room);
//            req.getRequestDispatcher("/statusOrder.jsp").forward(req, resp);
//        }
    }
}
