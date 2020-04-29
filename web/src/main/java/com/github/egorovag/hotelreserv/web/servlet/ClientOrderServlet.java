package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.OrderForClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.Condition;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.impl.DefaultRoomService;
import com.github.egorovag.hotelreserv.service.СlientService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.OrderService;
import com.github.egorovag.hotelreserv.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {

    private OrderService iorderService;
    private BlackListUsersService iconditionService;
    private RoomService iroomService;
    private СlientService iclientService;
    private Client client;

    @Override
    public void init() {
        iorderService = DefaultOrderService.getInstance();
        iconditionService = DefaultBlackListUsersService.getInstance();
        iroomService = DefaultRoomService.getInstance();
        iclientService = DefaultClientService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        String classOfAp = req.getParameter("classOfAp");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getUserId();
        int roomId = iroomService.readRoomIdService(numOfSeats, classOfAp);
        OrderClient orderWithoutId = new OrderClient(startDate, endDate, roomId, Condition.CONSIDERATION);
        OrderClient order = iorderService.saveOrder(orderWithoutId, clientId);
        req.getSession().setAttribute("order", order);
        Room room = iroomService.readRoomByIdService(roomId);
        req.getSession().setAttribute("room", room);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getUserId();
        List<OrderForClient> orderForClients = iorderService.readOrderForClientByClient_Id(clientId);
        req.setAttribute("orderForClients", orderForClients);
        req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req, resp);
    }
}
