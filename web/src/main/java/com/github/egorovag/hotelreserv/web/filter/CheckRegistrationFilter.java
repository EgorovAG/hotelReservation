package com.github.egorovag.hotelreserv.web.filter;

import com.github.egorovag.hotelreserv.service.impl.DefaultAuthUserService;
import com.github.egorovag.hotelreserv.service.AuthUserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/registration")
public class CheckRegistrationFilter implements Filter {

    private AuthUserService userService;
    @Override
    public void init(FilterConfig filterConfig) {
        userService = DefaultAuthUserService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");

        if (!userService.checkLogin(login)) {
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



