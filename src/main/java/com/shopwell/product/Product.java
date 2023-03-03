package com.shopwell.product;

import com.shopwell.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productName;
    private double productPrice;
    private ProductCategory productCategory;
    private int productQuantity;

    public Product(String productName, double productPrice, ProductCategory productCategory, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory=" + productCategory +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
