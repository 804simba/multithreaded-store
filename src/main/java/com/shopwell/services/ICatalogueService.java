package com.shopwell.services;

import com.shopwell.product.Product;

import java.util.List;
import java.util.Map;

public interface ICatalogueService {
    boolean isAvailable(List<Product> customerCart);
    void sellProducts(List<Product> customerCart);
    void addProducts(Map<String, Product> newProducts);
}
