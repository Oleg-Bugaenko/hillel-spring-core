package store.repository;

import lombok.Data;
import org.springframework.stereotype.Component;
import store.entity.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component("productRepository")
public class ProductRepository implements ProductRepoControl {
    private List<Product> productList;

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        return productList.stream().filter(e -> id == e.getId()).findAny().get();
    }

    @PostConstruct
    public void addProducts() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1, "Bread", 22.),
                new Product(2, "Water", 15.3),
                new Product(3, "Cola", 25.5),
                new Product(4, "Milk", 32.),
                new Product(5, "Salt", 10.2),
                new Product(6, "Sugar", 35.)
        ));
    }
}
