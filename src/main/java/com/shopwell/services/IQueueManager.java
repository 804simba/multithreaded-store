package com.shopwell.services;

import com.shopwell.customers.Customer;
import com.shopwell.staff.Cashier;

import java.util.Queue;

public interface IQueueManager {
    void addCustomersToQueue(Queue<Customer> queue);
//    void serveCustomersBasedOnFIFO(Cashier cashier);
//    void serveCustomersBasedOnNumberOfItems(Cashier cashier);
}
