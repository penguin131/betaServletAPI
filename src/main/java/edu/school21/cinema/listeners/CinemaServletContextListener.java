package edu.school21.cinema.listeners;

import edu.school21.cinema.config.CinemaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CinemaServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("springContext",
                new AnnotationConfigApplicationContext(CinemaConfig.class));
    }
}
