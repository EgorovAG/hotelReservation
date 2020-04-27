package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.api.Condition;
import com.github.egorovag.hotelreserv.service.OrderService;
import com.github.egorovag.hotelreserv.service.api.IorderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkPay")
public class CheckPayServlet extends HttpServlet {
    IorderService iorderService;
    @Override
    public void init() throws ServletException {
        iorderService = OrderService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int price = (int) req.getSession().getAttribute("price");
        int sum = Integer.parseInt(req.getParameter("sum"));


        if(sum == price) {
            int orderId= (int) req.getSession().getAttribute("orderId");
            iorderService.updateOrderList(orderId, Condition.PAID);
            req.getRequestDispatcher("/okPay.jsp").forward(req,resp);

        } else {
            req.setAttribute("Error","Вы ввели неверную сумму, попробуйте еще раз");
            req.getRequestDispatcher("/toPay.jsp").forward(req,resp);
        }
    }
}
