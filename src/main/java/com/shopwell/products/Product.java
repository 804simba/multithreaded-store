package com.shopwell.products;

import com.shopwell.enums.PRODUCTCATEGORY;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productName;
    private double productPrice;
    private PRODUCTCATEGORY productCategory;
    private int productQuantity;

    public Product(String productName, double productPrice, PRODUCTCATEGORY productCategory, int productQuantity) {
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
