package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.service.ShoppingCartService;
import main.internet.shop.service.UserService;

public class UserDeleteController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        userService.delete(id);
        shoppingCartService.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/user/all");
    }
}
