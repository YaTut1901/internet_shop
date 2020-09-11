package main.internet.shop.service;

import java.util.List;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart create(ShoppingCart cart);

    ShoppingCart addProduct(ShoppingCart cart, Product product);

    boolean deleteProduct(ShoppingCart cart, Product product);

    void clear(ShoppingCart cart);

    ShoppingCart getByUserId(Long userId);

    List<ShoppingCart> getAll();

    boolean deleteById(Long id);
}
