package edu.school21.cinema.servlets;

import edu.school21.cinema.models.AuthInfo;
import edu.school21.cinema.models.Image;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private CinemaRepository cinemaRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        List<AuthInfo> authInfos = cinemaRepository.getAuthInfos(user.getEmail());
        req.setAttribute("authInfos", authInfos);
        List<Image> images = cinemaRepository.getImages(user.getEmail());
        req.setAttribute("images", images);
        String authUrl = "/WEB-INF/jsp/profile.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(authUrl);
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
