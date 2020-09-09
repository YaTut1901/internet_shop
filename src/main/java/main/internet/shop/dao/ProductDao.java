package main.internet.shop.dao;

import main.internet.shop.model.Order;
import main.internet.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product create(Product product);

    Optional<Product> get(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean deleteById(Long id);

    boolean delete(Product t);
}
