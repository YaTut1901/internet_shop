package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.service.OrderService;
import main.internet.shop.service.ShoppingCartService;

public class CreateOrderController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        orderService.completeOrder(cart);
        request.setAttribute("message", "Order successfully created!");
        request.getRequestDispatcher("/WEB-INF/views/shopping-cart/products.jsp")
                .forward(request, response);
    }
}
