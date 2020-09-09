package main.internet.shop.dao;

import main.internet.shop.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart cart);

    Optional<ShoppingCart> get(Long id);

    List<ShoppingCart> getAll();

    ShoppingCart update(ShoppingCart cart);

    boolean deleteById(Long id);

    boolean delete(ShoppingCart cart);

    ShoppingCart getByUserId(Long userId);
}
