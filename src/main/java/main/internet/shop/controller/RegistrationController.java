package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.model.User;
import main.internet.shop.service.ShoppingCartService;
import main.internet.shop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConfirming = req.getParameter("pwd_confirm");
        String name = req.getParameter("name");
        if (password.equals(passwordConfirming)) {
            User user = userService.create(new User(name, login, password));
            shoppingCartService.create(new ShoppingCart(user.getId()));
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("message", "Wrong confirming!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
