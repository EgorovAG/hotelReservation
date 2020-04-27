package com.github.egorovag.hotelreserv.web.filter;

import com.github.egorovag.hotelreserv.model.AuthUser;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.api.Role;
import com.github.egorovag.hotelreserv.service.CheckUserService;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/registration")
public class CheckRegistrationFilter implements Filter {

    private IcheckUserService icheckUserService;
    @Override
    public void init(FilterConfig filterConfig) {
        icheckUserService = CheckUserService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");

        if (!icheckUserService.checkLogin(login)) {
            filterChain.doFilter(req,resp);
        } else {
            req.setAttribute("errorUser", "Пользователь с таким именем уже существует");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}



