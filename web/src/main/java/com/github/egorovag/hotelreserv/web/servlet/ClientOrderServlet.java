package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.*;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.impl.DefaultOrderService;
import com.github.egorovag.hotelreserv.service.impl.DefaultRoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {

    private OrderService orderService;
    private BlackListUsersService conditionService;
    private RoomService roomService;
    private СlientService clientService;
    private ServiceHotelService serviceHotelService;
    private Client client;
    private Service service;
    private ArrayList<Service> serviceList = null;
//    private ArrayList<OrderClient> orderClients = null;


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
        ClassRoom classOfAp = ClassRoom.valueOf(req.getParameter("classOfAp"));
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
//        if(typeOfService1 !=null && typeOfService1.isEmpty()){
//            serviceList.add(new )
//        }
//        String typeOfService1 = req.getParameter("typeOfService1");
//        String typeOfService2 = req.getParameter("typeOfService2");
//        String typeOfService3 = req.getParameter("typeOfService3");
//        String typeOfService4 = req.getParameter("typeOfService4");
//        String typeOfService5 = req.getParameter("typeOfService5");
//        String typeOfService6 = req.getParameter("typeOfService6");
//        String typeOfService7 = req.getParameter("typeOfService7");
// предположим что быбрали pool и wifi
//        String typeOfService1 = "pool";
//        String typeOfService2 = "wifi";
//        Service service1 = serviceHotelService.readServiceByTypeOfService(typeOfService1);
////        Service service2 = serviceHotelService.readServiceByTypeOfService(typeOfService2);
//        serviceList.add(service1);
//        serviceList.add(service2);

        Room room = roomService.readRoomByNumOfSeatsAndClassOfAp(numOfSeats, classOfAp);
        client = (Client) req.getSession().getAttribute("client");
        OrderClient order = new OrderClient(null, startDate, endDate, room.getId(),client.getId(),
                Condition.CONSIDERATION, room, client);
        orderService.saveOrder(order);
        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("room", room);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
//        ClassRoom classOfAp = ClassRoom.valueOf(req.getParameter("classOfAp"));
//        String startDate = req.getParameter("startDate");
//        String endDate = req.getParameter("endDate");
////        if(typeOfService1 !=null && typeOfService1.isEmpty()){
////            serviceList.add(new )
////        }
////        String typeOfService1 = req.getParameter("typeOfService1");
////        String typeOfService2 = req.getParameter("typeOfService2");
////        String typeOfService3 = req.getParameter("typeOfService3");
////        String typeOfService4 = req.getParameter("typeOfService4");
////        String typeOfService5 = req.getParameter("typeOfService5");
////        String typeOfService6 = req.getParameter("typeOfService6");
////        String typeOfService7 = req.getParameter("typeOfService7");
//// предположим что быбрали pool и wifi
//        String typeOfService1 = "pool";
//        String typeOfService2 = "wifi";
//
//        client = (Client) req.getSession().getAttribute("client");
//        Room room = roomService.readRoomByNumOfSeatsAndClassOfAp(numOfSeats, classOfAp);
//        Service service1 = serviceHotelService.readServiceByTypeOfService(typeOfService1);
//        Service service2 = serviceHotelService.readServiceByTypeOfService(typeOfService2);
//        serviceList.add(service1);
//        serviceList.add(service2);
//        OrderClient orderClient = new OrderClient(null, startDate, endDate,
//                Condition.CONSIDERATION, room, client);
////        orderClient.getServices().addAll(serviceList);
////        service1.getOrderClients().add(orderClient);
////        service2.getOrderClients().add(orderClient);
//
//        OrderClient order = orderService.saveOrder(orderClient);
////        OrderClient order = orderService.saveOrder(client, numOfSeats, classOfAp, startDate, endDate,
////                Condition.CONSIDERATION, serviceList);
////        if(typeOfService1.equals("pool")){
////
////        }
//        req.getSession().setAttribute("order", order);
////        Room room = roomService.readRoomById(roomId);
//        req.getSession().setAttribute("room", order.getRoom());
//        req.getRequestDispatcher("/order.jsp").forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = (Client) req.getSession().getAttribute("client");
        int clientId = client.getId();
        List<OrderForClient> orderForClients = orderService.readOrderForClientByClient_Id(clientId);
        req.setAttribute("orderForClients", orderForClients);
        req.getRequestDispatcher("/statusOrderNEW.jsp").forward(req, resp);
    }
}
