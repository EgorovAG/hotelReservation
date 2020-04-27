package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.OrderForClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.api.Condition;
import com.github.egorovag.hotelreserv.service.ClientService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;
import com.github.egorovag.hotelreserv.service.OrderService;
import com.github.egorovag.hotelreserv.service.RoomService;
import com.github.egorovag.hotelreserv.service.api.IclientService;
import com.github.egorovag.hotelreserv.service.api.IblackListUsersService;
import com.github.egorovag.hotelreserv.service.api.IorderService;
import com.github.egorovag.hotelreserv.service.api.IroomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {

    private IorderService iorderService;
    private IblackListUsersService iconditionService;
    private IroomService iroomService;
    private IclientService iclientService;

    @Override
    public void init() {
        iorderService= OrderService.getInstance();
        iconditionService = BlackListUsersService.getInstance();
        iroomService = RoomService.getInstance();
        iclientService = ClientService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        String classOfAp = req.getParameter("classOfAp");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        Client client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getUserId();
        int roomId = iroomService.readRoomIdService(numOfSeats,classOfAp);
        OrderClient orderWithoutId = new OrderClient(startDate,endDate,roomId, Condition.CONSIDERATION);
        OrderClient order =iorderService.saveOrder(orderWithoutId, clientId);
        req.getSession().setAttribute("order", order);
        Room room = iroomService.readRoomByIdService(roomId);
        req.getSession().setAttribute("room", room);
        req.getRequestDispatcher("/order.jsp").forward(req,resp);


//        OrderClient clientOrder = new OrderClient(authUser.getLogin(), numOfSeats, classOfAp, stayTime);
//        iclientOrderService.save(clientOrder);
//        req.setAttribute("clientOrder", clientOrder);
//        req.getRequestDispatcher("/order.jsp").forward(req,resp);

        List<OrderForClient> orderForClientList = iorderService.readOrderForClientByClient_Id(clientId);
        req.getRequestDispatcher("/statusOrderNew.jsp").forward(req,resp);








    }
}
