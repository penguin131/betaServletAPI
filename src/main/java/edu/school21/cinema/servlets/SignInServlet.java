package edu.school21.cinema.servlets;

import edu.school21.cinema.models.AuthInfo;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.CinemaRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

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

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private PasswordEncoder encoder;
    private CinemaRepository cinemaRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = cinemaRepository.getUserForEmail(email);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        } else {
            if (encoder.matches(password, user.getPassword())) {
                cinemaRepository.saveAuthInfo(createAuthInfo(user, req));
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                resp.sendRedirect(req.getContextPath() + "/signIn");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String authUrl = "/WEB-INF/html/auth.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(authUrl);
        dispatcher.forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.cinemaRepository = springContext.getBean(CinemaRepository.class);
        this.encoder = springContext.getBean(PasswordEncoder.class);
        super.init(config);
    }

    private AuthInfo createAuthInfo(User user, HttpServletRequest req) {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUser(user);
        String clientIp = req.getRemoteAddr();
        if (clientIp.equals("0:0:0:0:0:0:0:1")) {
            clientIp = "127.0.0.1";
        }
        authInfo.setIp(clientIp);
        authInfo.setTime(System.currentTimeMillis());
        return authInfo;
    }
}
