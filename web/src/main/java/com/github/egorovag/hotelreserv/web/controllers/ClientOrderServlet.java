package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.ServiceHotel;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ClientOrderServlet {
    private static final Logger log = LoggerFactory.getLogger(ClientOrderServlet.class);

    private final OrderService orderService;
//    private BlackListUsersService conditionService;
    private final RoomService roomService;
//    private ClientService clientService;
    private final ServiceHotelService serviceHotelService;

    public ClientOrderServlet(OrderService orderService, RoomService roomService, ServiceHotelService serviceHotelService) {
        this.orderService = orderService;
        this.roomService = roomService;
        this.serviceHotelService = serviceHotelService;
    }

    //    private Service service;
//    private Room room;
//        private ArrayList<OrderClient> orderClients = null;


    @GetMapping("/clientOrder")
    public String doGet(HttpServletRequest req) {
        List<ServiceHotel> serviceList = new ArrayList<>();
        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        ClassRoom classOfAp = ClassRoom.valueOf(req.getParameter("classOfAp"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(req.getParameter("startDate"),formatter);
        LocalDate endDate = LocalDate.parse(req.getParameter("endDate"),formatter);
        String typeOfService1 = req.getParameter("typeOfService1");
        String typeOfService2 = req.getParameter("typeOfService2");
        String typeOfService3 = req.getParameter("typeOfService3");
        String typeOfService4 = req.getParameter("typeOfService4");
        String typeOfService5 = req.getParameter("typeOfService5");
        String typeOfService6 = req.getParameter("typeOfService6");
        String typeOfService7 = req.getParameter("typeOfService7");

        if (typeOfService1 != null && !typeOfService1.isEmpty()) {
            ServiceHotel service1 = serviceHotelService.readServiceByTypeOfService(typeOfService1);
            serviceList.add(service1);
        }

        if (typeOfService2 != null && !typeOfService2.isEmpty()) {
            ServiceHotel service2 = serviceHotelService.readServiceByTypeOfService(typeOfService2);
            serviceList.add(service2);
        }

        if (typeOfService3 != null && !typeOfService3.isEmpty()) {
            ServiceHotel service3 = serviceHotelService.readServiceByTypeOfService(typeOfService3);
            serviceList.add(service3);
        }

        if (typeOfService4 != null && !typeOfService4.isEmpty()) {
            ServiceHotel service4 = serviceHotelService.readServiceByTypeOfService(typeOfService4);
            serviceList.add(service4);
        }

        if (typeOfService5 != null && !typeOfService5.isEmpty()) {
            ServiceHotel service5 = serviceHotelService.readServiceByTypeOfService(typeOfService5);
            serviceList.add(service5);
        }

        if (typeOfService6 != null && !typeOfService6.isEmpty()) {
            ServiceHotel service6 = serviceHotelService.readServiceByTypeOfService(typeOfService6);
            serviceList.add(service6);
        }

        if (typeOfService7 != null && !typeOfService7.isEmpty()) {
            ServiceHotel service7 = serviceHotelService.readServiceByTypeOfService(typeOfService7);
            serviceList.add(service7);
        }

        Room room = roomService.readRoomByNumOfSeatsAndClassOfAp(numOfSeats, classOfAp);
        Client client = (Client) req.getSession().getAttribute("client");
        OrderClient order = new OrderClient(null, startDate, endDate, room.getId(), client.getId(),
                Condition.CONSIDERATION);
        order = orderService.saveOrder(order, room, client);

        if(!serviceList.isEmpty()) {
            serviceHotelService.saveServiceListForOrder(serviceList, order.getOrderId());
            serviceList = serviceHotelService.readServiceListByOrderId(order.getOrderId());
        }

        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("room", room);
        req.getSession().setAttribute("serviceList", serviceList);
        return"forward:/order.jsp";
    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        client = (Client) req.getSession().getAttribute("client");
//        int clientId = client.getId();
//        List<OrderForClient> orderForClients = orderService.readOrderForClientByClientId(clientId);
//        req.setAttribute("orderForClients", orderForClients);
//        req.getRequestDispatcher("/hotel/statusOrder").forward(req, resp);
//    }
}
