package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.service.OrderService;

public class UserOrdersController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        request.setAttribute("orders", orderService.getUserOrders(userId));
        request.getRequestDispatcher("/WEB-INF/views/order/user-orders.jsp")
                .forward(request, response);
    }
}
