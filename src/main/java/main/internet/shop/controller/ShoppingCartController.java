package main.internet.shop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.service.ShoppingCartService;

public class ShoppingCartController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Product> allProducts =
                shoppingCartService.getByUserId(userId).getProducts();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/shopping-cart/products.jsp").forward(req, resp);
    }
}
