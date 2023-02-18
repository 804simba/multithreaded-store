package com.shopwell.services;

import com.shopwell.customers.Customer;
import com.shopwell.products.Product;
import com.shopwell.staff.Cashier;

public interface ICashierService {
    void checkOutCustomer(Customer customer);
    void updateProductQuantity(Product product, int quantity);
    void setDailySalesAccount(double amount, Object other);
    void issueReceipt(Customer customer, double totalPrice);
}
