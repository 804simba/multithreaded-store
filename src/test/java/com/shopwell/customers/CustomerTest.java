package com.shopwell.customers;

import com.shopwell.Store;
import com.shopwell.enums.PRODUCTCATEGORY;
import com.shopwell.enums.Role;
import com.shopwell.products.Product;
import com.shopwell.services.ICashierService;
import com.shopwell.services.IManagerService;
import com.shopwell.services.servicesimplementation.CashierServiceImpl;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import com.shopwell.staff.Cashier;
import com.shopwell.staff.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer;
    IManagerService ms;
    Manager manager;
    Cashier cashier;
    ICashierService cs;
    Product product1;
    Product product2;
    Store store;
    @BeforeEach
    void init() {
        store = new Store("ShopWell store", 500000.0);
        customer = new Customer("John Doe", 85000.0);
        product1 = new Product("Bread", 2500, PRODUCTCATEGORY.GROCERIES, 100);
        product2 = new Product("Root Beer", 2500, PRODUCTCATEGORY.BEVERAGES, 100);
        manager = new Manager("Abraham Lincoln", Role.MANAGER, store);
        ms = new ManagerServiceImpl(manager, store);
        ms.addProduct(product1);
        ms.addProduct(product2);
        cashier = new Cashier("Kelly Rowland", Role.CASHIER, store);
        ms.hireStaff(cashier);
        cs = new CashierServiceImpl(cashier, store);
        customer.addProductToCart(product1, 2);
        customer.addProductToCart(product2, 2);
    }
    @Test
    void addProductToCart() {
        int expected = 2;
        int actual = customer.getCart().size();
        assertEquals(expected, actual);
    }

    @Test
    void makePayment() {
        cs.checkOutCustomer(customer);
        double expected = 75000.0;
        double actual = customer.getCreditCardBalance();
        assertEquals(expected, actual);
    }
}