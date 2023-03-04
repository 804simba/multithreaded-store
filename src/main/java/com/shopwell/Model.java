package com.shopwell;

import com.shopwell.customer.Customer;
import com.shopwell.enums.ProductCategory;
import com.shopwell.product.Product;
import com.shopwell.store.Store;

import java.util.HashMap;
import java.util.Map;

public class Model {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>> Main thread has started <<<<<<<<<<<<<");

        Store store = new Store("Shop Well", 100000.0);

        Product rice = new Product("Rice", 2000, ProductCategory.GROCERIES, 10);
        Product beer = new Product("Beer", 200, ProductCategory.BEVERAGES, 10);
        Product soap = new Product("Soap", 100, ProductCategory.TOILETRIES, 30);

        Map<String, Product> newProducts = new HashMap<>();
        newProducts.put(rice.getName(), rice);
        newProducts.put(beer.getName(), beer);
        newProducts.put(soap.getName(), soap);

        store.addProducts(newProducts);

        System.out.println("New customers...");
        Customer customer1 = new Customer("Shawn Michael", 500.0, store);
        Customer customer2 = new Customer("Booker T", 7000.0, store);
        Customer customer3 = new Customer("Triple H", 2000.0, store);
        Customer customer4 = new Customer("John Cena", 12000.0, store);
        Customer customer5 = new Customer("John Morrison", 20000.0, store);
        Customer customer6 = new Customer("Randy Orton", 20000.0, store);
        Customer customer7 = new Customer("Roman Reigns", 20000.0, store);
        Customer customer8 = new Customer("Chris Benoit", 20000.0, store);
        Customer customer9 = new Customer("Vince McMason", 20000.0, store);
        Customer customer10 = new Customer("Eugene", 20000.0, store);
        Customer customer11 = new Customer("Kane", 20000.0, store);
        Customer customer12 = new Customer("The Undertaker", 20000.0, store);
        Customer customer13 = new Customer("Ric Flair", 20000.0, store);

        customer1.addProductToCart(rice, 5);
        customer1.addProductToCart(beer, 5);
        customer1.addProductToCart(soap, 5);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer2.addProductToCart(rice, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer3.addProductToCart(beer, 11);
        customer3.addProductToCart(rice, 1);
        customer3.addProductToCart(soap, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer4.addProductToCart(soap, 1);
        customer4.addProductToCart(beer, 10);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer5.addProductToCart(soap, 1);
        customer5.addProductToCart(beer, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer6.addProductToCart(soap, 1);
        customer6.addProductToCart(beer, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer7.addProductToCart(soap, 3);
        customer7.addProductToCart(beer, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer8.addProductToCart(soap, 1);
        customer8.addProductToCart(beer, 5);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer9.addProductToCart(soap, 1);
        customer9.addProductToCart(beer, 12);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer10.addProductToCart(rice, 1);
        customer10.addProductToCart(beer, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer11.addProductToCart(beer, 1);
        customer11.addProductToCart(soap, 1);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer12.addProductToCart(soap, 1);
        customer12.addProductToCart(beer, 2);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer13.addProductToCart(soap, 1);
        customer13.addProductToCart(beer, 5);
        System.out.println("------------------>");
        System.out.println("------------------>");

        customer1.startCustomerThread();
        customer2.startCustomerThread();
        customer3.startCustomerThread();
        customer4.startCustomerThread();
        customer5.startCustomerThread();
        customer6.startCustomerThread();
        customer7.startCustomerThread();
        customer8.startCustomerThread();
        customer9.startCustomerThread();
        customer10.startCustomerThread();
        customer11.startCustomerThread();
        customer12.startCustomerThread();
        customer13.startCustomerThread();
    }
}