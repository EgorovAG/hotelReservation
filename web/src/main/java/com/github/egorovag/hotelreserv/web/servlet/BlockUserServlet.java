package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.service.impl.DefaultBlackListUsersService;
import com.github.egorovag.hotelreserv.service.BlackListUsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/blockUser")
public class BlockUserServlet extends HttpServlet {
    BlackListUsersService iblackListUsersService;

    @Override
    public void init() throws ServletException {
        iblackListUsersService = DefaultBlackListUsersService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(iblackListUsersService.checkBlackUserById(id)) {
            req.setAttribute("error", "Такой пользователь уже заблокирован!");
            req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);
        } else {
            iblackListUsersService.saveBlackListUserById(id);}
        req.setAttribute("error", "Выбранный пользователь заблокирован!");
        req.getRequestDispatcher("/registratedUsers.jsp").forward(req,resp);


    }
}
