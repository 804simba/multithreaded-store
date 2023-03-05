package com.shopwell.customer;

import com.shopwell.product.Product;
import com.shopwell.services.ICartService;
import com.shopwell.store.Store;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
                try {
                    makePayment();
                    store.sellProducts(cart);
                } catch (RuntimeException e) {
                    System.err.println(name + ": Cannot make purchase, " + e.getMessage());
                }
            } else {
                // simulate a customer waiting...
                try {
                    TimeUnit.SECONDS.sleep(9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isAvailableAfterWait = store.isAvailable(cart);
                if (isAvailableAfterWait) {
                    try {
                        makePayment();
                        store.sellProducts(cart);
                        String message = String.format("%s your orders will be delivered soon...", name);
                        System.out.println(message);
                    } catch (RuntimeException e) {
                        System.err.println(name + ": Cannot make purchase, " + e.getMessage());
                    }
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
            throw new RuntimeException("Insufficient balance..");
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
        try {
            TimeUnit.SECONDS.sleep(9);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", timeOfArrival='" + timeOfArrival + '\'' +
                '}';
    }
}
