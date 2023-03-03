package com.shopwell;

import com.shopwell.customer.Customer;
import com.shopwell.enums.ProductCategory;
import com.shopwell.product.Product;
import com.shopwell.services.ICatalogueService;
import com.shopwell.services.implementations.CatalogueServiceImpl;
import com.shopwell.store.Store;

public class Model {
    public static void main(String[] args) {
        ICatalogueService iCatalogueService = new CatalogueServiceImpl();
        Store store = new Store("Shop Well", 100000.0, iCatalogueService);

        Product rice = new Product("Rice", 2000, ProductCategory.GROCERIES, 110);
        Product beer = new Product("Beer", 20, ProductCategory.GROCERIES, 100);
        Product soap = new Product("Soap", 100, ProductCategory.TOILETRIES, 300);

        System.out.println("New customers...");
        Customer customer1 = new Customer("Hov", 50000000.0, store);
        Customer customer2 = new Customer("Musa", 70000000.0, store);
        Customer customer3 = new Customer("Hannah", 200000000.0, store);
        Customer customer4 = new Customer("James", 200000000.0, store);
        Customer customer5 = new Customer("John", 200000000.0, store);

        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(rice, 5);
        System.out.println("------------------>");

        customer2.addProductToCart(rice, 1);
        System.out.println("------------------>");

        customer3.addProductToCart(rice, 11);
        customer3.addProductToCart(beer, 1);
        customer3.addProductToCart(soap, 1);
        System.out.println("------------------>");

        customer4.addProductToCart(soap, 1);
        customer4.addProductToCart(soap, 1);
        customer4.addProductToCart(soap, 1);
        System.out.println("------------------>");

        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(soap, 1);
        System.out.println("------------------>");

    }
}