package com.shopwell.staff;

import com.shopwell.Customer;
import com.shopwell.PRODUCTCATEGORY;
import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.ICashierService;
import com.shopwell.services.IManagerService;
import com.shopwell.services.servicesimplementation.CashierServiceImpl;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Manager manager;
    Cashier cashier;
    Cashier cashier2;
    ICashierService cs;
    Customer customer;
    Product vodka;
    Store store;

    IManagerService ms;
    @BeforeEach
    void init() {
        store = new Store("Shopwell", 100000.0);
        manager = new Manager("Janet Collins", Role.MANAGER, store);
        cashier = new Cashier("Oliver Kahn", Role.CASHIER, store);
        cashier2 = new Cashier("Jack Daniels", Role.CASHIER, store);
        customer = new Customer("Kanye West", 200000.0);
        vodka = new Product("Vodka", 12000.0, PRODUCTCATEGORY.BEVERAGES, 100);
        ms = new ManagerServiceImpl(manager, store);
        cs = new CashierServiceImpl(cashier, store);
        ms.addProduct(vodka);
    }

    @Test
    void shouldFireStaff() {
        ms.hireStaff(cashier);
        ms.hireStaff(cashier2);
        ms.fireStaff(cashier);
        int actual = store.getCashiersList().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void shouldHireStaff() {
        ms.hireStaff(cashier);
        ms.hireStaff(cashier2);
        int expected = 2;
        int actual = store.getCashiersList().size();
        assertEquals(expected, actual);
        assertNotEquals(0, actual);
    }

    @Test
    void shouldAddDaySalesToCompanyAccount() {
        ms.hireStaff(cashier);
        customer.addProductToCart(vodka, 2);
        cs.checkOutCustomer(customer);
        manager.addSalesToCompanyAccount(store.getDailySalesAccount());
        double expected = 124000.0;
        double actual = store.getAccountBalance();
        assertEquals(expected, actual);
    }

}