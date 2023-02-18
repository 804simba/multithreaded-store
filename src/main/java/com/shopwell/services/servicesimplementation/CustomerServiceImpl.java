package com.shopwell.services.servicesimplementation;

import com.shopwell.customers.Customer;
import com.shopwell.products.Product;
import com.shopwell.services.ICustomerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
