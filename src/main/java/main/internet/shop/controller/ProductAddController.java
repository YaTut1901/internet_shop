package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.service.ProductService;

public class ProductAddController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/product/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("productName");
        Double price = Double.parseDouble(req.getParameter("productPrice"));
        Product product = new Product(name, price);
        productService.create(product);
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
