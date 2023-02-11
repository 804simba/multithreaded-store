package com.shopwell.staff;

import com.shopwell.Store;
import com.shopwell.services.ManagerService;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Manager manager;
    Cashier cashier;
    Cashier cashier2;
    Store store;

    ManagerService ms;
    @BeforeEach
    void init() {
        store = new Store("Shopwell", 100000.0);
        manager = new Manager("Janet Collins", Designation.MANAGER, store);
        cashier = new Cashier("Oliver Kahn", Designation.CASHIER, store);
        cashier2 = new Cashier("Jack Daniels", Designation.CASHIER, store);
        ms = new ManagerServiceImpl(manager, store);
    }

    @Test
    void fireStaff() {
        ms.hireStaff(cashier);
        ms.hireStaff(cashier2);
        ms.fireStaff(cashier);
        int actual = store.getCashiersList().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

}