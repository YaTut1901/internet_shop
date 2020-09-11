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
    public static final Long TEMP_USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        shoppingCartService.create(new ShoppingCart(TEMP_USER_ID));
        Long id = Long.parseLong(req.getParameter("productId"));
        Product productToBuy = productService.get(id);
        ShoppingCart userCart = shoppingCartService.getByUserId(TEMP_USER_ID);
        userCart.getProducts().add(productToBuy);
        req.getRequestDispatcher("/WEB-INF/views/product/success-buying.jsp").forward(req, resp);
    }
}
