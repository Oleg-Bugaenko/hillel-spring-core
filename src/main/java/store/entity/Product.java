package store.entity;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String title;
    private Double price;

    public Product(Integer id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id= " + id + ", title= " + title + ", price= " + price;
    }
}
