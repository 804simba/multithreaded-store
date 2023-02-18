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

public class Main {
    public static void main(String[] args) {
        Store store = new Store("shopwell", 100000);

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 11);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 10);
//
        Product soap = new Product("Soap", 100, PRODUCTCATEGORY.TOILETRIES, 30);
        Product perfume = new Product("Dior Perfume", 10, PRODUCTCATEGORY.TOILETRIES, 20);
        Product samsung = new Product("Samsung TV", 1000, PRODUCTCATEGORY.ELECTRONICS, 20);
        Product iphone = new Product("iPhone 14 Pro", 20000, PRODUCTCATEGORY.ELECTRONICS, 20);

        Manager manager = new Manager("Jay", Role.MANAGER, store);
        Cashier cashier = new Cashier("Sarah Mac", Role.CASHIER, store);

        IManagerService ms = new ManagerServiceImpl(manager, store);
        ms.hireStaff(cashier);
        ICashierService cs = new CashierServiceImpl(cashier, store);

        ms.addProduct(rice);

        System.out.println("New customer.");
        Customer customer = new Customer("Hov", 20000);

        customer.addProductToCart(rice, 1);

        cs.checkOutCustomer(customer);

        System.out.println("Balance " + store.getDailySalesAccount());
        System.out.println("Rice: " + rice.getProductQuantity());

    }
}