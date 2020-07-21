package com.github.egorovag.hotelreserv.web.controllers;

import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class PaginationRegisteredUsers  {

    private AuthUserService authUserService;

    public PaginationRegisteredUsers(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("/paginationRegisteredUsers")
    public String doGet(HttpServletRequest req, HttpSession session) {
        int page = 1;
        int maxResultsPage = 4;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<AuthUserWithClientDTO> authUserWithClients = authUserService.readListAuthUserWithClientDTOPagination(page,
                maxResultsPage);
        int countResult = authUserService.countAuthUserWithClient();
        int noOfPages = (int) Math.ceil((countResult * 1.0) / maxResultsPage);

        session.setAttribute("authUserWithClients", authUserWithClients);
        session.setAttribute("noOfPages", noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("maxResultsPage", maxResultsPage);

        return "registeredUsers";
    }
}
