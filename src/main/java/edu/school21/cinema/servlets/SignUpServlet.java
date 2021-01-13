package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.CinemaRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private CinemaRepository cinemaRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (cinemaRepository.getUserForEmail(req.getParameter("email")) == null) {
            req.setCharacterEncoding("UTF-8");
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setFamily(req.getParameter("family"));
            user.setEmail(req.getParameter("email"));
            user.setPhoneNumber(req.getParameter("phoneNumber"));
            user.setPassword(req.getParameter("password"));
            cinemaRepository.saveUser(user);
            resp.sendRedirect(req.getContextPath() + "/signIn");
        } else {
            resp.sendError(409, "User already exist");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nextHTML = "/WEB-INF/html/register.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextHTML);
        dispatcher.forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.cinemaRepository = springContext.getBean(CinemaRepository.class);
        super.init(config);
    }
}
