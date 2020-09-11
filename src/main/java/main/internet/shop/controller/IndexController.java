package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.model.User;
import main.internet.shop.service.ProductService;
import main.internet.shop.service.ShoppingCartService;
import main.internet.shop.service.UserService;

public class IndexController extends HttpServlet {
    public static final Long TEMP_USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService.create(new Product("sword", 34D));
        productService.create(new Product("spear", 45D));
        productService.create(new Product("shield", 22D));
        userService.create(new User("test"));
        shoppingCartService.create(new ShoppingCart(TEMP_USER_ID));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
