package com.github.egorovag.hotelreserv.web.servlet;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.ClientService;
import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.impl.DefaultClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/paginationRegistratedUsers")
public class PaginationRegistratedUsers extends HttpServlet {

    private AuthUserService authUserService;
    private ClientService clientService;
    private AuthUserService checkUserService;
    int page;
    int maxResultsPage;


    @Override
    public void init() throws ServletException {
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        page = 1;
        maxResultsPage = 4;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<AuthUserWithClient> authUserWithClients = authUserService.readListAuthUserWithClientPagination(page, maxResultsPage);
        int countResult = authUserService.countAuthUserWithClient();
        int noOfPages = (int) Math.ceil((countResult * 1.0) / maxResultsPage);

        session.setAttribute("authUserWithClients", authUserWithClients);
        session.setAttribute("noOfPages", noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("maxResultsPage", maxResultsPage);

        req.getRequestDispatcher("/registratedUsers.jsp").forward(req, resp);
    }


}
