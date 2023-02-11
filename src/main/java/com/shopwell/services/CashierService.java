package com.shopwell.services;

import com.shopwell.Customer;
import com.shopwell.Product;
import com.shopwell.staff.Cashier;

public interface CashierService {
    void checkOutCustomer(Customer customer);
    void updateProductQuantity(Product product, int quantity);
    void setDailySalesAccount(double amount, Object other);
    void issueReceipt(Customer customer, double totalPrice);
}
