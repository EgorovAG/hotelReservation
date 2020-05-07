package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.Condition;
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

    private OrderService orderService;
    private BlackListUsersService conditionService;
    private RoomService roomService;
    private СlientService clientService;
    private Client client;


    @Override
    public void init() {
        orderService = DefaultOrderService.getInstance();
        conditionService = DefaultBlackListUsersService.getInstance();
        roomService = DefaultRoomService.getInstance();
        clientService = DefaultClientService.getInstance();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        String classOfAp = req.getParameter("classOfAp");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String typeOfService1 = req.getParameter("typeOfService1");
        String typeOfService2 = req.getParameter("typeOfService2");
        String typeOfService3 = req.getParameter("typeOfService3");
        String typeOfService4 = req.getParameter("typeOfService4");
        String typeOfService5 = req.getParameter("typeOfService5");
        String typeOfService6 = req.getParameter("typeOfService6");
        String typeOfService7 = req.getParameter("typeOfService7");

        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getUserId();
        int roomId = roomService.readRoomIdService(numOfSeats, classOfAp);
        OrderClient orderWithoutId = new OrderClient(startDate, endDate, roomId, Condition.CONSIDERATION);
        OrderClient order = orderService.saveOrder(orderWithoutId, clientId);
        if(typeOfService1.equals("pool")){


        }
        req.getSession().setAttribute("order", order);
        Room room = roomService.readRoomByIdService(roomId);
        req.getSession().setAttribute("room", room);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getUserId();
        List<OrderForClient> orderForClients = orderService.readOrderForClientByClient_Id(clientId);
        req.setAttribute("orderForClients", orderForClients);
        req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req, resp);
    }
}
