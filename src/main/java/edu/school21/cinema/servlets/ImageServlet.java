package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.CinemaRepository;
import edu.school21.cinema.services.ImageFileService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/images")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImageServlet extends HttpServlet {
    private CinemaRepository cinemaRepository;
    private ImageFileService imageFileService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Collection<Part> parts = req.getParts();
        for (Part part : parts) {
            Image newImage = new Image();
            User user = (User) req.getSession().getAttribute("user");
            newImage.setUser(user);
            newImage.setName(part.getSubmittedFileName());
            newImage.setSize(part.getSize());
            newImage.setMime(part.getContentType());
            long imageId = cinemaRepository.saveImage(newImage);
            imageFileService.saveImage(imageId, part);
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        req.getParameter()
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.cinemaRepository = springContext.getBean(CinemaRepository.class);
        this.imageFileService = springContext.getBean(ImageFileService.class);
        super.init(config);
    }
}
