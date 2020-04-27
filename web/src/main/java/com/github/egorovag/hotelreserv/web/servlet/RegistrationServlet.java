package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.api.Role;
import com.github.egorovag.hotelreserv.service.ClientService;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;
import com.github.egorovag.hotelreserv.service.CheckUserService;
import com.github.egorovag.hotelreserv.service.api.IclientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private IcheckUserService icheckUserService;
    private IclientService iclientService;

    @Override
    public void init() {
        icheckUserService = CheckUserService.getInstance();
        iclientService = ClientService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        AuthUser authUser = icheckUserService.saveAuthUser(login, password, Role.USER);
        System.out.println(authUser);
        Client client = new Client(firstName, secondName, email, phone, authUser.getId());
        System.out.println(client);
        iclientService.saveClient(client);
        req.getSession().setAttribute("authUser", authUser);
        req.getSession().setAttribute("client", client);
        req.getRequestDispatcher("personalArea.jsp").forward(req, resp);
    }
}
