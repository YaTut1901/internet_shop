package main.internet.shop;

import main.internet.shop.lib.Injector;
import main.internet.shop.models.Product;
import main.internet.shop.service.ProductService;

public class Application {
    private static Injector injector =Injector.getInstance("main.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product axe = new Product("Axe", 23.5);
        productService.create(axe);
        Product spear = new Product("Spear", 21.5);
        productService.create(spear);
        Product sword = new Product("Sword", 10.3);
        productService.create(sword);
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Spear update:");
        Product productToUpdate = new Product("Spear", 23.0);
        productToUpdate.setId(spear.getId());
        productService.update(productToUpdate);
        System.out.println(productService.get(spear.getId()));
        System.out.println("Deleting axe:");
        productService.delete(axe.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
