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
    public static final Long TEMP_USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShoppingCart cart = shoppingCartService.getByUserId(TEMP_USER_ID);
        orderService.completeOrder(cart);
        request.setAttribute("message", "Order successfully created!");
        request.getRequestDispatcher("/WEB-INF/views/shopping-cart/products.jsp")
                .forward(request, response);
    }
}
