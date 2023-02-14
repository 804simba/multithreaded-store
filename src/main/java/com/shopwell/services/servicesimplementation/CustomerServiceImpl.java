package com.shopwell.services.servicesimplementation;

import com.shopwell.Customer;
import com.shopwell.Product;
import com.shopwell.services.ICustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    Customer customer;

    @Override
    public void addToCart(Product product, int quantity) {
        customer.addProductToCart(product, quantity);
    }

    @Override
    public void makePayment(double amount) {
        customer.makePayment(amount);
    }
}
