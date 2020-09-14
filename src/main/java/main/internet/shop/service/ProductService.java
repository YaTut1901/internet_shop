package main.internet.shop.service;

import java.util.List;
import main.internet.shop.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    List<Product> getAll();

    Product update(Product t);

    boolean delete(Long id);
}
