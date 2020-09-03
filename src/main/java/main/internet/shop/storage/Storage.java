package main.internet.shop.storage;

import main.internet.shop.models.Order;
import main.internet.shop.models.Product;
import main.internet.shop.models.ShoppingCart;
import main.internet.shop.models.User;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static final Map<Long, Order> orderStorage = new HashMap<>();
    private static Integer orderAmount = 0;
    private static final Map<Long, Product> productStorage = new HashMap<>();
    private static Integer productAmount = 0;
    private static final Map<Long, ShoppingCart> shoppingCartStorage = new HashMap<>();
    private static Integer shoppingCartAmount = 0;
    private static final Map<Long, User> userStorage = new HashMap<>();
    private static Integer userAmount = 0;

    public static Map<Long, Order> getOrderStorage() {
        return orderStorage;
    }

    public static Map<Long, Product> getProductStorage() {
        return productStorage;
    }

    public static Map<Long, ShoppingCart> getShoppingCartStorage() {
        return shoppingCartStorage;
    }

    public static Map<Long, User> getUserStorage() {
        return userStorage;
    }

    public static Integer getOrderAmount() {
        return orderAmount;
    }

    public static void setOrderAmount(Integer orderAmount) {
        Storage.orderAmount = orderAmount;
    }

    public static Integer getProductAmount() {
        return productAmount;
    }

    public static void setProductAmount(Integer productAmount) {
        Storage.productAmount = productAmount;
    }

    public static Integer getShoppingCartAmount() {
        return shoppingCartAmount;
    }

    public static void setShoppingCartAmount(Integer shoppingCartAmount) {
        Storage.shoppingCartAmount = shoppingCartAmount;
    }

    public static Integer getUserAmount() {
        return userAmount;
    }

    public static void setUserAmount(Integer userAmount) {
        Storage.userAmount = userAmount;
    }
}
