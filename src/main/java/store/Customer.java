package store;

import lombok.Data;
import store.cart.Cart;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {
    private String name;

    private List<Cart> carts;

    public Customer(String name) {
        this.name = name;
        this.carts = new ArrayList<>();
    }

}
