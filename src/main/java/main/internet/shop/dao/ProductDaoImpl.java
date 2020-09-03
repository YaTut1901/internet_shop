package main.internet.shop.dao;

import main.internet.shop.lib.Dao;
import main.internet.shop.models.Product;
import main.internet.shop.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        product.setId(getId());
        Storage.getProductStorage().put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Optional.ofNullable(Storage.getProductStorage().get(id));
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(Storage.getProductStorage().values());
    }

    @Override
    public Product update(Product product) {
        Long inputProductId = product.getId();
        Product existedProduct = Storage.getProductStorage().get(inputProductId);
        if (existedProduct == null) {
            create(product);
            return product;
        }
        Storage.getProductStorage().put(inputProductId, product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product currentProduct = Storage.getProductStorage().get(id);
        if (currentProduct == null) {
            return false;
        }
        Storage.getProductStorage().remove(id);
        return true;
    }

    private static Long getId() {
        Integer currentProductAmount = Storage.getProductAmount();
        Storage.setProductAmount(currentProductAmount + 1);
        return (long) Storage.getProductAmount();
    }
}
