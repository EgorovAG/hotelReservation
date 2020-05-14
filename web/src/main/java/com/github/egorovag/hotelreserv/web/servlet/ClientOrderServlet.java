package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.*;
import com.github.egorovag.hotelreserv.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {

    private OrderService orderService;
    private BlackListUsersService conditionService;
    private RoomService roomService;
    private ClientService clientService;
    private ServiceHotelService serviceHotelService;
    private Client client;
    private Service service;
    private Room room;
    //    private ArrayList<OrderClient> orderClients = null;



    @Override
    public void init() {
        orderService = DefaultOrderService.getInstance();
        conditionService = DefaultBlackListUsersService.getInstance();
        roomService = DefaultRoomService.getInstance();
        clientService = DefaultClientService.getInstance();
        serviceHotelService = DefaultServiceHotelService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> serviceList = new ArrayList<>();
        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        ClassRoom classOfAp = ClassRoom.valueOf(req.getParameter("classOfAp"));
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String typeOfService1 = req.getParameter("typeOfService1");
        String typeOfService2 = req.getParameter("typeOfService2");
        String typeOfService3 = req.getParameter("typeOfService3");
        String typeOfService4 = req.getParameter("typeOfService4");
        String typeOfService5 = req.getParameter("typeOfService5");
        String typeOfService6 = req.getParameter("typeOfService6");
        String typeOfService7 = req.getParameter("typeOfService7");

             //что-то не работает
        if (typeOfService1 != null && !typeOfService1.isEmpty()) {
            Service service1 = serviceHotelService.readServiceByTypeOfService(typeOfService1);
            serviceList.add(service1);
        }

        if (typeOfService2 != null && !typeOfService2.isEmpty()) {
            Service service2 = serviceHotelService.readServiceByTypeOfService(typeOfService2);
            serviceList.add(service2);
        }

        if (typeOfService3 != null && !typeOfService3.isEmpty()) {
            Service service3 = serviceHotelService.readServiceByTypeOfService(typeOfService3);
            serviceList.add(service3);
        }

        if (typeOfService4 != null && !typeOfService4.isEmpty()) {
            Service service4 = serviceHotelService.readServiceByTypeOfService(typeOfService4);
            serviceList.add(service4);
        }

        if (typeOfService5 != null && !typeOfService5.isEmpty()) {
            Service service5 = serviceHotelService.readServiceByTypeOfService(typeOfService5);
            serviceList.add(service5);
        }

        if (typeOfService6 != null && !typeOfService6.isEmpty()) {
            Service service6 = serviceHotelService.readServiceByTypeOfService(typeOfService6);
            serviceList.add(service6);
        }

        if (typeOfService7 != null && !typeOfService7.isEmpty()) {
            Service service7 = serviceHotelService.readServiceByTypeOfService(typeOfService7);
            serviceList.add(service7);
        }

        Room room = roomService.readRoomByNumOfSeatsAndClassOfAp(numOfSeats, classOfAp);
        client = (Client) req.getSession().getAttribute("client");
        OrderClient order = new OrderClient(null, startDate, endDate, room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        order = orderService.saveOrder(order);

        if(!serviceList.isEmpty()) {
            serviceHotelService.saveServiceListForOrder(serviceList, order.getOrderId());
            serviceList = serviceHotelService.readServiceListByOrderId(order.getOrderId());
        }

        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("room", room);
        req.getSession().setAttribute("serviceList", serviceList);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getId();
        List<OrderForClient> orderForClients = orderService.readOrderForClientByClientId(clientId);
        req.setAttribute("orderForClients", orderForClients);
        req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req, resp);
    }
}
