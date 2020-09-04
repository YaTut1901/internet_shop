package main.internet.shop.storage;

import java.util.ArrayList;
import java.util.List;
import main.internet.shop.model.Order;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.model.User;

public class Storage {

    public static List<Order> orders = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> carts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    private static long userId = 0L;
    private static long orderId = 0L;
    private static long productId = 0L;
    private static long cartId = 0L;

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }
}
