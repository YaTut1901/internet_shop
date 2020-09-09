package main.internet.shop.dao;

import main.internet.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order id);

    boolean deleteById(Long id);

    boolean delete(Order order);

    List<Order> getUserOrders(Long userId);
}
