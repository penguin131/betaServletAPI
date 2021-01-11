package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            HttpServletResponse response = (HttpServletResponse) res;
//            response.sendRedirect("/signIn"); for ex00
            response.sendError(403);
        } else {
            chain.doFilter(req, res);
        }
    }

    public void init (FilterConfig config) {
    }

    public void destroy() {
    }
}
