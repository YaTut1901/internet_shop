package main.internet.shop.dao;

import java.util.List;
import main.internet.shop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> getUserOrders(Long userId);
}
