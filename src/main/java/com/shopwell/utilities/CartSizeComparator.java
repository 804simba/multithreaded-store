package com.shopwell.utilities;

import com.shopwell.customers.Customer;

import java.util.Comparator;

public class CartSizeComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return Integer.compare(o2.getCart().size(), o1.getCart().size());
    }
}
