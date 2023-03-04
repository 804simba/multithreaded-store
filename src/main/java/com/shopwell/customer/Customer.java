package com.shopwell.customer;

import com.shopwell.product.Product;
import com.shopwell.services.ICartService;
import com.shopwell.store.Store;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Customer implements Runnable, ICartService<Product> {
    private final String name;
    private Double creditCardBalance;
    private final List<Product> cart;
    private final String timeOfArrival;
    private final Store store;

    public Customer(String name, Double creditCardBalance, Store store) {
        this.name = name;
        this.creditCardBalance = creditCardBalance;
        this.cart = new ArrayList<>();
        this.timeOfArrival = LocalDateTime.now().toString();
        this.store = store;
    }

    @Override
    public void addProductToCart(Product product, int quantity) {
        Product selectedProduct;
        selectedProduct = new Product(product.getName(), product.getPrice(), product.getProductCategory(), quantity);
        cart.add(selectedProduct);
        System.out.printf("%s added %d units of %s to cart", this.getName(), selectedProduct.getQuantity(), selectedProduct.getName());
        System.out.println();
    }
    public void buyProducts() {
        synchronized (store) {
            boolean isAvailable = store.isAvailable(cart);
            if (isAvailable) {
                makePayment();
                store.sellProducts(cart);
            } else {
                // simulate a customer waiting...
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isAvailableAfterWait = store.isAvailable(cart);
                if (isAvailableAfterWait) {
                    makePayment();
                    store.sellProducts(cart);
                    String message = String.format("%s your orders will be delivered soon...", name);
                    System.out.println(message);
                } else {
                    System.out.println(this.name + " has given up on buying the products in the cart...");
                    System.out.println(this.name + " has cancelled their orders...");
                }
            }
        }
    }

    public void makePayment() {
        double totalPrice = 0.0;
        for (Product product : getCart()) {
            totalPrice += product.getPrice();
        }
        if (totalPrice >= creditCardBalance) {
            System.out.println(this.name + " does not have enough money to buy these items >>>");
        } else {
            this.creditCardBalance -= totalPrice;
        }
    }

    @Override
    public void run() {
        buyProducts();
    }
    public void startCustomerThread() {
        Thread c = new Thread(this, this.getName());
        c.start();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", timeOfArrival='" + timeOfArrival + '\'' +
                '}';
    }
}
