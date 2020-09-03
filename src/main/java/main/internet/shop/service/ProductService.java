package main.internet.shop.service;

import main.internet.shop.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);

    Optional<Product> get(Long id);

    List<Product> getAll();

    Product update(Product t);

    boolean delete(Long id);
}
