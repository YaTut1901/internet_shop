package main.internet.shop;

import main.internet.shop.lib.Injector;
import main.internet.shop.model.Order;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.model.User;
import main.internet.shop.service.OrderService;
import main.internet.shop.service.ProductService;
import main.internet.shop.service.ShoppingCartService;
import main.internet.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("main.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product axe = new Product("Axe", 23.5);
        productService.create(axe);
        Product spear = new Product("Spear", 21.5);
        productService.create(spear);
        Product sword = new Product("Sword", 10.3);
        productService.create(sword);
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Spear update:");
        Product productToUpdate = new Product("Spear", 23.0);
        productToUpdate.setId(spear.getId());
        productService.update(productToUpdate);
        System.out.println(productService.get(spear.getId()));
        System.out.println("Deleting axe:");
        productService.delete(axe.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User firstUser = new User("John", "john123", "qwerty");
        userService.create(firstUser);
        User secondUser = new User("Jonathan", "john233", "qwersdty");
        userService.create(secondUser);
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        userService.delete(1L);
        System.out.println("Deleting first user");
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        System.out.println("Getting second user");
        System.out.println(userService.get(2L));
        userService.create(firstUser);
        User tempUser = userService.get(3L);
        tempUser.setName("Bob");
        userService.update(tempUser);
        System.out.println("Updating 3 user");
        System.out.println(userService.get(3L));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart firstCart = new ShoppingCart(3L);
        firstCart.getProducts().add(new Product("sword", 34.5));
        shoppingCartService.create(firstCart);
        ShoppingCart secondCart = new ShoppingCart(2L);
        secondCart.getProducts().add(new Product("axe", 56D));
        shoppingCartService.create(secondCart);
        for (ShoppingCart cart : shoppingCartService.getAll()) {
            System.out.println(cart);
        }
        System.out.println("Getting by user id = 3");
        System.out.println(shoppingCartService.getByUserId(3L));
        Product biggerSpear = new Product("spear", 45D);
        shoppingCartService.addProduct(firstCart, biggerSpear);
        System.out.println("Adding product");
        System.out.println(firstCart);
        shoppingCartService.deleteProduct(firstCart, biggerSpear);
        System.out.println("Deleting product");
        System.out.println(firstCart);
        shoppingCartService.clear(firstCart);
        System.out.println("Cleaning firstCart");
        System.out.println(firstCart);

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order firstOrder = new Order(3L);
        Order secondOrder = new Order(2L);
        orderService.create(firstOrder);
        orderService.create(secondOrder);
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
        System.out.println("Getting user`s orders");
        for (Order order : orderService.getUserOrders(3L)) {
            System.out.println(order);
        }
        System.out.println("Completing order");
        System.out.println(orderService.completeOrder(firstCart));
        orderService.delete(1L);
        System.out.println("Deleting Order");
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
    }
}
