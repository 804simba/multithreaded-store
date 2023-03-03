package com.shopwell.product;

import com.shopwell.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String name;
    private double price;
    private ProductCategory productCategory;
    private int quantity;

    public Product(String name, double price, ProductCategory productCategory, int quantity) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + name + '\'' +
                ", productPrice=" + price +
                ", productCategory=" + productCategory +
                ", productQuantity=" + quantity +
                '}';
    }
}
