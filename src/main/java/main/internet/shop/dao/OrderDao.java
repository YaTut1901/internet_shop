package main.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import main.internet.shop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean deleteById(Long id);

    boolean delete(Order order);
}
