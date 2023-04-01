package store.cart;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import store.entity.Product;
import store.repository.ProductRepoControl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component("cart")
@Scope("prototype")
public class Cart {
    private static int numberCart = 0;
    private String personCart;
    private List<Product> products = new ArrayList<>();
    private ProductRepoControl productRepository;

    public Cart(){
        numberCart++;
    }

    @Autowired
    public void setProductRepository(ProductRepoControl productRepository) {
        this.productRepository = productRepository;
    }
    public void setPersonCart(String personCart) {
        this.personCart = personCart + "-"+ numberCart;
    }

    public String getPersonCart() {
        return personCart;
    }

    public void addProduct(int id) {
        products.add(productRepository.getProductById(id));
    }

    public boolean removeProduct(int id) {
        for (Product product: products) {
            if (product.getId()==id) {
                products.remove(product);
                return true;
            }
        }
        System.out.printf("Product with id=%s not found\n", id);
        return false;
    }

    public List<Product> getAllProducts() {
        return products;
    }

}
