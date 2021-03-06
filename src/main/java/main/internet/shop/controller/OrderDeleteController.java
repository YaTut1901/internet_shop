package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.service.OrderService;

public class OrderDeleteController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        orderService.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}
