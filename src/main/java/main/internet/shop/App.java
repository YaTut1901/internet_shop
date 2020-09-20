package main.internet.shop;

import main.internet.shop.dao.ProductDao;
import main.internet.shop.dao.jdbcimpl.ProductDaoJavaDataBaseConnectivityImpl;
import main.internet.shop.model.Product;

public class App {
    public static void main(String[] args) {
        ProductDao dao = new ProductDaoJavaDataBaseConnectivityImpl();
        Product product = new Product("sdasdddd", 23D);
        product.setId(1L);
        dao.update(product);
        dao.deleteById(1L);
        System.out.println(dao.getAll());
    }
}
