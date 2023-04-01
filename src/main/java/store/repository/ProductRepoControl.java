package store.repository;

import store.entity.Product;

import java.util.List;

public interface ProductRepoControl {
    List<Product> getAll();
    Product getProductById(int id);

}
