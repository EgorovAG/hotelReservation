package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClient;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class PaginationRegistratedUsers  {

    private static final Logger log = LoggerFactory.getLogger(PaginationRegistratedUsers.class);

    private AuthUserService authUserService;

    public PaginationRegistratedUsers(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    //    private ClientService clientService;
//    private AuthUserService checkUserService;


    @GetMapping("/paginationRegistratedUsers")
    public String doGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        int page = 1;
        int maxResultsPage = 4;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<AuthUserWithClient> authUserWithClients = authUserService.readListAuthUserWithClientPagination(page,
                maxResultsPage);
        int countResult = authUserService.countAuthUserWithClient();
        int noOfPages = (int) Math.ceil((countResult * 1.0) / maxResultsPage);

        session.setAttribute("authUserWithClients", authUserWithClients);
        session.setAttribute("noOfPages", noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("maxResultsPage", maxResultsPage);

        return "registratedUsers";
    }
}
