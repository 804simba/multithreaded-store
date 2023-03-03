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
        selectedProduct = new Product(product.getProductName(), product.getProductPrice(), product.getProductCategory(), quantity);
        cart.add(selectedProduct);
        System.out.printf("%s added %d units of %s to cart", this.getName(), selectedProduct.getProductQuantity(), selectedProduct.getProductName());
        System.out.println();
    }
    @Override
    public List<Product> getCart() {
        return cart;
    }

    public void makePayment(double amount) {
        creditCardBalance -= amount;
        System.out.printf("%s\n", this.getName());
        System.out.printf("Debit alert: %.2f  Available Balance: %.2f\n", amount, getCreditCardBalance());
        System.out.println();
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
