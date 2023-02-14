package com.shopwell.services;

import com.shopwell.Product;

public interface ICustomerService {
    void addToCart(Product product, int quantity);
    void makePayment(double amount);
}
