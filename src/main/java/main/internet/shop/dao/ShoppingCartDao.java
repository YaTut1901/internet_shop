package main.internet.shop.dao;

import main.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

    ShoppingCart getByUserId(Long userId);
}
