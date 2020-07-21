package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final RoomService roomService;
    private final ServiceHotelService serviceHotelService;

    public OrderController(OrderService orderService, RoomService roomService, ServiceHotelService serviceHotelService) {
        this.orderService = orderService;
        this.roomService = roomService;
        this.serviceHotelService = serviceHotelService;
    }

    @PostMapping("/clientOrder")
    public String saveOrder(HttpServletRequest req) {
        List<ServiceHotel> serviceList = new ArrayList<>();
        int numOfSeats = Integer.parseInt(req.getParameter("numOfSeats"));
        ClassRoom classOfAp = ClassRoom.valueOf(req.getParameter("classOfAp"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(req.getParameter("startDate"), formatter);
        LocalDate endDate = LocalDate.parse(req.getParameter("endDate"), formatter);
        Set<String> typeOfServiceLists = new HashSet<>();
        typeOfServiceLists.add(req.getParameter("typeOfService1"));
        typeOfServiceLists.add(req.getParameter("typeOfService2"));
        typeOfServiceLists.add(req.getParameter("typeOfService3"));
        typeOfServiceLists.add(req.getParameter("typeOfService4"));
        typeOfServiceLists.add(req.getParameter("typeOfService5"));
        typeOfServiceLists.add(req.getParameter("typeOfService6"));
        typeOfServiceLists.add(req.getParameter("typeOfService7"));

        for (String typeOfService : typeOfServiceLists) {
            if (typeOfService != null) {
                ServiceHotel service = serviceHotelService.readServiceByTypeOfService(typeOfService);
                serviceList.add(service);
            }
        }

        Room room = roomService.readRoomByNumOfSeatsAndClassOfAp(numOfSeats, classOfAp);
        Client client = (Client) req.getSession().getAttribute("client");
        OrderClient order = new OrderClient(null, startDate, endDate, room.getId(), client.getId(),
                Condition.CONSIDERATION);
        order = orderService.saveOrder(order, room, client);

        if (!serviceList.isEmpty()) {
            serviceHotelService.saveServiceListForOrder(serviceList, order.getOrderId());
            serviceList = serviceHotelService.readServiceListByOrderId(order.getOrderId());
        }

        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("room", room);
        req.getSession().setAttribute("serviceList", serviceList);
        log.info("orderClient with orderId:{} saved", order.getOrderId());
        return "viewOrder";
    }

    @PostMapping("/checkPay")
    public String updateOrderList(@RequestParam(value = "sum") int sum, HttpSession session, Model model) {
        int price = (int) session.getAttribute("price");

        if (sum == price) {
            int orderId = (int) session.getAttribute("orderId");
            orderService.updateOrderList(orderId, Condition.PAID);
            log.info("orderClient with orderId:{} updated", orderId);
            return "successfulPayment";
        } else {
            model.addAttribute("error", "error.wrongAmount");
            return "orderPayment";
        }
    }

    @GetMapping("/orderList")
    public String readOrderForAdmin(Model model) {
        List<OrderForAdminDTO> orderForAdmins = orderService.readOrderListForAdminDTO();
        List<OrderClient> orderClients = orderService.readOrderClientList();
        if (orderForAdmins == null || orderForAdmins.isEmpty()) {
            model.addAttribute("orderForAdmins", null);
        } else {
            model.addAttribute("orderForAdmins", orderForAdmins);
            model.addAttribute("orderClients", orderClients);
        }
        return "orderListForAdmin";
    }

    @PostMapping("/orderList")
    public String deleteOrUpdateOrderList(@RequestParam(value = "orderId") int orderId,
                                          @RequestParam(value = "condition") String cond, Model model) {
        if (cond.equals("DELETE")) {
            orderService.deleteOrderByOrderId(orderId);
            log.info("orderClient with orderId:{} deleted", orderId);
        } else {
            Condition condition = Condition.valueOf(cond);
            if (orderService.readConditionByOrderId(orderId) == Condition.PAID) {
                model.addAttribute("error", "error.orderPaid");
            } else {
                orderService.updateOrderList(orderId, condition);
                log.info("orderClient with orderId:{} updated", orderId);
            }
        }
        List<OrderForAdminDTO> orderForAdmins = orderService.readOrderListForAdminDTO();
        List<OrderClient> orderClients = orderService.readOrderClientList();
        model.addAttribute("orderForAdmins", orderForAdmins);
        model.addAttribute("orderClients", orderClients);
        return "redirect:/orderList";
    }

    @GetMapping("/statusOrder")
    public String readOrderForClient(HttpSession session, Model model) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        List<OrderForClientDTO> orderForClients = orderService.readOrderForClientDTOByClientId(authUser.getClient().getId());
        List<OrderClient> orderClients = orderService.readOrderClientListByClientId(authUser.getClient().getId());
        if (orderForClients == null || orderForClients.isEmpty()) {
            model.addAttribute("orderForClients", null);
        } else {
            model.addAttribute("orderForClients", orderForClients);
            model.addAttribute("orderClients", orderClients);
        }
        return "orderListForClient";
    }

    @GetMapping("/toClientOrderJsp")
    public String toClientOrderJsp() {
        return "clientMakesAnOrder";
    }
}
