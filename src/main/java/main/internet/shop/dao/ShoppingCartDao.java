package main.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import main.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart cart);

    Optional<ShoppingCart> get(Long id);

    List<ShoppingCart> getAll();

    ShoppingCart getByUserId(Long userId);

    ShoppingCart update(ShoppingCart cart);

    boolean deleteById(Long id);

    boolean delete(ShoppingCart cart);
}
