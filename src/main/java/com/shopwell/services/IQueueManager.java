package com.shopwell.services;

import com.shopwell.customers.Customer;
import com.shopwell.staff.Cashier;

public interface IQueueManager {
    void addCustomerToQueue(Customer customer);
    void serveCustomersBasedOnFIFO(Cashier cashier);
    void serveCustomersBasedOnNumberOfItems(Cashier cashier);
}
