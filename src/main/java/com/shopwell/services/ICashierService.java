package com.shopwell.services;

import com.shopwell.Customer;
import com.shopwell.Product;

public interface ICashierService {
    void checkOutCustomer(Customer customer);
    void updateProductQuantity(Product product, int quantity);
    void setDailySalesAccount(double amount, Object other);
    void issueReceipt(Customer customer, double totalPrice);
}
