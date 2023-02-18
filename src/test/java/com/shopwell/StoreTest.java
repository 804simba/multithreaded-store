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
    Store store2;
    Cashier cashier;
    ICashierService cs;
    Customer customer;
    Customer customer2;
    Customer customer3;
    Product product1;
    Product product2;
    Product product3;
    IManagerService ms;
    IManagerService msNumberOfItems;

    @BeforeEach
    void init() {
        store = new Store("shopwell", 100000.0);
        manager = new Manager("John Cena", Role.MANAGER, store);
        product1 = new Product("Tissue", 20.0, PRODUCTCATEGORY.TOILETRIES, 10);
        product2 = new Product("Bread", 120.0, PRODUCTCATEGORY.GROCERIES, 10);
        product3 = new Product("Samsung TV", 100.0, PRODUCTCATEGORY.ELECTRONICS, 10);
        cashier = new Cashier("The Undertaker", Role.CASHIER, store);
        customer = new Customer("Burna Boy", 120000.0);
        customer2 = new Customer("Kanye West", 400000.0);
        customer3 = new Customer("Drake Graham", 500000.0);
        customer.addProductToCart(product1, 2);
        customer2.addProductToCart(product1, 5);
        cs = new CashierServiceImpl(cashier, store);
        ms = new ManagerServiceImpl(manager, store);
        ms.addProduct(product1);
        ms.addProduct(product2);
        ms.addProduct(product3);
        ms.hireStaff(cashier);

        // Store based on higher Priority for customers who bought the most items.
        store2 = new Store("shopwell2", 100000.0, new CustomerComparator());
        msNumberOfItems = new ManagerServiceImpl(manager, store2);
        msNumberOfItems.addProduct(product1);
        msNumberOfItems.addProduct(product2);
        msNumberOfItems.addProduct(product3);
        msNumberOfItems.hireStaff(cashier);
    }
    @Test
    void checkAccountBalance() {
        assertEquals(100000.0, store.getAccountBalance());
    }

    @Test
    void shouldUpdateStoreAccountBalance() {
        Double expected = 100240.0;
        cs.checkOutCustomer(customer);
        ms.addSalesToCompanyAccount(store.getDailySalesAccount());
        Double actual = store.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddCashier() {
        assertEquals(1, store.getCashiersList().size());
    }

    @Test
    void shouldAddProduct() {
        ms.addProduct(new Product("Yam", 12.0, PRODUCTCATEGORY.GROCERIES, 10));
        assertEquals(4, store.getProductsList().size());
    }

    @Test
    void shouldAddCustomerTOQueue() {
        store.addCustomerToQueue(customer);
        store.addCustomerToQueue(customer2);
        Integer expected = 2;
        Integer actual = store.getCustomerQueue().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldServeCustomersBasedOnFIFO() {
        customer3.addProductToCart(product1, 1);
        customer3.addProductToCart(product2, 1);
        customer3.addProductToCart(product3, 1);
        store.addCustomerToQueue(customer);
        store.addCustomerToQueue(customer2);
        store.addCustomerToQueue(customer3);
        String expected = "Burna Boy";
        assert store.getCustomerQueue().peek() != null;
        String actual = store.getCustomerQueue().peek().getName();
        assertEquals(expected, actual);
    }

    @Test
    void shouldServeCustomersBasedOnNumberOfBoughtItems() {
        customer2.addProductToCart(product2, 2);
        customer2.addProductToCart(product3, 2);
        customer2.addProductToCart(product3, 2);

        customer3.addProductToCart(product1, 1);
        customer3.addProductToCart(product2, 1);
        customer3.addProductToCart(product3, 1);
        customer3.addProductToCart(product1, 1);
        customer3.addProductToCart(product2, 1);



        store2.addCustomerToQueue(customer);
        store2.addCustomerToQueue(customer2);
        store2.addCustomerToQueue(customer3);


        Customer customerAtTheFront = store2.getCustomerQueue().peek();
        String expected = "Drake Graham";
        System.out.println(store2.getCustomerQueue());
        assert customerAtTheFront != null;
        String actual = customerAtTheFront.getName();
        assertEquals(expected, actual);
    }
}