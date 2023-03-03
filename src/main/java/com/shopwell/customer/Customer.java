package com.shopwell.customer;

import com.shopwell.product.Product;
import com.shopwell.services.ICartService;
import com.shopwell.store.Store;
import lombok.Getter;

import java.time.LocalDateTime;
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
        this.cart = new LinkedList<>();
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

    }

    public void makePayment(double amount) {
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

    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", timeOfArrival='" + timeOfArrival + '\'' +
                '}';
    }
}
