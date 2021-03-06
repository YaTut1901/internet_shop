package main.internet.shop.service.impl;

import java.util.List;
import main.internet.shop.dao.OrderDao;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.Order;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order completeOrder(ShoppingCart cart) {
        Order order = new Order(cart.getUserId());
        order.getProducts().addAll(List.copyOf(cart.getProducts()));
        cart.getProducts().clear();
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.deleteById(id);
    }
}
