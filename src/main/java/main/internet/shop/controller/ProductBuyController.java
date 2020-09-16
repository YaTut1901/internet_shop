package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.service.ProductService;
import main.internet.shop.service.ShoppingCartService;

public class ProductBuyController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        Product product = productService.get(productId);
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        shoppingCartService.addProduct(cart, product);
        req.getRequestDispatcher("/WEB-INF/views/product/success-buying.jsp")
                .forward(req, resp);
    }
}
