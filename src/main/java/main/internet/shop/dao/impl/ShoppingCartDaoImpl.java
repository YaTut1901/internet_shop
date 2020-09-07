package main.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import main.internet.shop.dao.ShoppingCartDao;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.storage.Storage;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart cart) {
        Storage.addShoppingCart(cart);
        return cart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.carts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.carts;
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return Storage.carts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        IntStream.range(0, Storage.carts.size())
                .filter(index -> Storage.carts.get(index).getId().equals(cart.getId()))
                .forEach(index -> Storage.carts.set(index, cart));
        return cart;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.carts.removeIf(cart -> cart.getId().equals(id));
    }

    @Override
    public boolean delete(ShoppingCart cart) {
        return deleteById(cart.getId());
    }
}
