package main.internet.shop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.service.OrderService;

public class OrderDetailsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        List<Product> products = orderService.get(id).getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/order/details.jsp")
                .forward(request, response);
    }
}
