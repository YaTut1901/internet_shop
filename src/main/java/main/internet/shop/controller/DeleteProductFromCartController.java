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

public class DeleteProductFromCartController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("id"));

        Product product = productService.get(productId);
        ShoppingCart cart = shoppingCartService.getByUserId(TEMP_USER_ID);
        shoppingCartService.deleteProduct(cart, product);
        response.sendRedirect(request.getContextPath() + "/shopping-cart/products");
    }
}
