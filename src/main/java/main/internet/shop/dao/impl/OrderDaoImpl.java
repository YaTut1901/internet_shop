package main.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import main.internet.shop.dao.OrderDao;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.Order;
import main.internet.shop.storage.Storage;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(index -> Storage.orders.get(index).getId().equals(order.getId()))
                .forEach(index -> Storage.orders.set(index, order));
        return order;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }
}
