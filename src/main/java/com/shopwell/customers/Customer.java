package com.shopwell.customers;

import com.shopwell.products.Product;
import lombok.Getter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Customer implements Comparable<Customer> {
    private final String name;
    private Double creditCardBalance;
    private final List<Product> cart;
    private final Long timeOfArrival = System.currentTimeMillis();

    public Customer(String name, Double creditCardBalance) {
        this.name = name;
        this.creditCardBalance = creditCardBalance;
        this.cart = new LinkedList<>();
    }

    public void addProductToCart(Product product, Integer quantity) {
        Product selectedProduct;
        selectedProduct = new Product(product.getProductName(), product.getProductPrice(), product.getProductCategory(), quantity);
        cart.add(selectedProduct);
        System.out.printf("%s added %d units of %s to cart", this.getName(), selectedProduct.getProductQuantity(), selectedProduct.getProductName());
        System.out.println();
    }

    public void makePayment(double amount) {
        creditCardBalance -= amount;
        System.out.printf("%s\n", this.getName());
        System.out.printf("Debit alert: %.2f  Available Balance: %.2f\n", amount, getCreditCardBalance());
        System.out.println();
    }

    @Override
    public int compareTo(Customer o) {
        return this.getTimeOfArrival().compareTo(o.getTimeOfArrival());
    }
}
