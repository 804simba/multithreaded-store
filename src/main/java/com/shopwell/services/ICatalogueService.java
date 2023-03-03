package com.shopwell.services;

import com.shopwell.product.Product;

import java.util.List;

public interface ICatalogueService {
    boolean isAvailable(List<Product> customerCart);
    void sellProducts(List<Product> customerCart);
}
