package com.shopwell;

import com.shopwell.customers.Customer;
import com.shopwell.enums.PRODUCTCATEGORY;
import com.shopwell.products.Product;
import com.shopwell.services.ICashierService;
import com.shopwell.services.IManagerService;
import com.shopwell.services.servicesimplementation.CashierServiceImpl;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import com.shopwell.staff.Cashier;
import com.shopwell.enums.Role;
import com.shopwell.staff.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    Manager manager;
    Store store;
    Cashier cashier;
    ICashierService cs;
    Customer customer;
    Product product;
    IManagerService ms;

    @BeforeEach
    void init() {
        store = new Store("shopwell", 100000.0);
        manager = new Manager("John Cena", Role.MANAGER, store);
        product = new Product("Tissue", 120.0, PRODUCTCATEGORY.TOILETRIES, 10);
        cashier = new Cashier("The Undertaker", Role.CASHIER, store);
        customer = new Customer("Jude King", 120000);
        customer.addProductToCart(product, 2);
        cs = new CashierServiceImpl(cashier, store);
        ms = new ManagerServiceImpl(manager, store);
        ms.addProduct(product);
        ms.hireStaff(cashier);
    }
    @Test
    void checkAccountBalance() {
        assertEquals(100000.0, store.getAccountBalance());
    }

    @Test
    void updateStoreAccountBalance() {
        double expected = 100240.0;
        cs.checkOutCustomer(customer);
        ms.addSalesToCompanyAccount(store.getDailySalesAccount());
        double actual = store.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    void addCashier() {
        assertEquals(1, store.getCashiersList().size());
    }

    @Test
    void addProducts() {
        ms.addProduct(new Product("Yam", 12.0, PRODUCTCATEGORY.GROCERIES, 10));
        assertEquals(2, store.getProductsList().size());
    }
}