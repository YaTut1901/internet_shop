package main.internet.shop.service.impl;

import java.util.List;
import main.internet.shop.dao.ShoppingCartDao;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        return shoppingCartDao.create(cart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart cart, Product product) {
        cart.getProducts().add(product);
        return shoppingCartDao.update(cart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart cart, Product product) {
        boolean result = cart.getProducts().remove(product);
        shoppingCartDao.update(cart);
        return result;
    }

    @Override
    public void clear(ShoppingCart cart) {
        cart.getProducts().clear();
        shoppingCartDao.update(cart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId);
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return shoppingCartDao.deleteById(id);
    }
}
