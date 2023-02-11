package com.shopwell.services.servicesimplementation;

import com.shopwell.Customer;
import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.CashierService;
import com.shopwell.staff.Cashier;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CashierServiceImpl implements CashierService {
    Cashier cashier;
    Store store;

    @Override
    public void checkOutCustomer(Customer customer) {
        cashier.checkOutCustomer(customer);
    }

    @Override
    public void updateProductQuantity(Product product, int quantity) {
        cashier.updateProductQuantity(product, quantity);
    }

    @Override
    public void setDailySalesAccount(double amount, Object other) {
        store.setDailySalesAccount(store.getAccountBalance() + amount, cashier);
    }

    @Override
    public void issueReceipt(Customer customer, double totalPrice) {
        cashier.issueReceipt(customer, totalPrice);
    }
}
