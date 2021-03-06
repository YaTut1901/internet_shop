package main.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.Product;
import main.internet.shop.model.User;
import main.internet.shop.model.role.UserRole;
import main.internet.shop.service.ProductService;
import main.internet.shop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productService.create(new Product("sword", 34D));
        productService.create(new Product("spear", 45D));
        productService.create(new Product("shield", 22D));
        User admin = new User("admin", "admin", "123");
        admin.addUserRole(UserRole.of("ADMIN"));
        User userAdmin = new User("userAdmin", "userAdmin", "1234");
        userAdmin.addUserRole(UserRole.of("ADMIN"));
        userAdmin.addUserRole(UserRole.of("USER"));
        userService.create(admin);
        userService.create(userAdmin);
    }
}
