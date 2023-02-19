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
import com.shopwell.utilities.CustomerComparator;

public class Main {
    public static void main(String[] args) {
        // Store that attends to customers based on FIFO
        Store store = new Store("shopwell", 100000.0);

        // Store that attends to customers based on the customers with the highest number of items in their cart.
        Store store2 = new Store("shopwell", 100000.0, new CustomerComparator());

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 110);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 100);
        Product soap = new Product("Soap", 100, PRODUCTCATEGORY.TOILETRIES, 300);

        Manager manager = new Manager("Jay", Role.MANAGER, store2);
        Cashier cashier = new Cashier("Sarah Mac", Role.CASHIER, store2);

        IManagerService ms = new ManagerServiceImpl(manager, store2);
        ms.hireStaff(cashier);
        ICashierService cs = new CashierServiceImpl(cashier, store2);

        ms.addProduct(rice);
        ms.addProduct(beer);
        ms.addProduct(soap);
//
        System.out.println("New customers...");
        Customer customer1 = new Customer("Hov", 20000.0);
        Customer customer2 = new Customer("Musa", 20000.0);
        Customer customer3 = new Customer("Hannah", 200000000.0);
        Customer customer4 = new Customer("James", 200000000.0);
        Customer customer5 = new Customer("James", 200000000.0);

//
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);

        customer2.addProductToCart(rice, 1);

        customer3.addProductToCart(rice, 11);
        customer3.addProductToCart(beer, 1);
        customer3.addProductToCart(soap, 1);

        customer4.addProductToCart(soap, 1);
        customer4.addProductToCart(soap, 1);
        customer4.addProductToCart(soap, 1);

        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);


        store2.addCustomerToQueue(customer4);
        store2.addCustomerToQueue(customer2);
        store2.addCustomerToQueue(customer3);
        store2.addCustomerToQueue(customer1);
        store2.addCustomerToQueue(customer5);
//        store2.serveCustomersBasedOnNumberOfItems(cashier);
        System.out.println(store2.getCustomerQueue());
    }
}