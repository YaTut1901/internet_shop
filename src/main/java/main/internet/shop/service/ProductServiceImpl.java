package main.internet.shop.service;

import main.internet.shop.dao.ProductDao;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.models.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Optional<Product> get(Long id) {
        return productDao.get(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
