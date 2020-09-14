package main.internet.shop.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.User;
import main.internet.shop.service.UserService;

public class EntranceController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/entrance.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        try {
            User user = userService.getByLogin(login);
            if (user.getPassword().equals(password)) {
                resp.sendRedirect(req.getContextPath() + "/main-menu");
            }
        } catch (NoSuchElementException e) {
            req.setAttribute("message", "There is no user with such login! Please register!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
