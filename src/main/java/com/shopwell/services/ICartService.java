package com.shopwell.services;

import com.shopwell.product.Product;

import java.util.List;

public interface ICartService<T> {
    void addProductToCart(Product product, int quantity);
}
