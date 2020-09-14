package main.internet.shop.service.impl;

import java.util.List;
import main.internet.shop.dao.ProductDao;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.Product;
import main.internet.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow();
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
        return productDao.deleteById(id);
    }
}
