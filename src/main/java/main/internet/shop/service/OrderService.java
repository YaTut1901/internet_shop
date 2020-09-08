package main.internet.shop.service;

import java.util.List;
import main.internet.shop.model.Order;
import main.internet.shop.model.ShoppingCart;

public interface OrderService {
    Order create(Order order);

    Order completeOrder(ShoppingCart cart);

    List<Order> getUserOrders(Long userId);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
