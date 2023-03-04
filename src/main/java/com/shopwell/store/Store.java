package com.shopwell.store;

import com.shopwell.product.Product;
import com.shopwell.services.IAccountManager;
import com.shopwell.services.ICatalogueService;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Store implements IAccountManager, ICatalogueService {
    private final String name;
    private Double accountBalance = 0.0;
    final private Map<String, Product> storeCatalogue;

    public Store(String name, Double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        this.storeCatalogue = new ConcurrentHashMap<>();
    }
    @Override
    public void addProducts(Map<String, Product> newProducts) {
        storeCatalogue.putAll(newProducts);
    }
    @Override
    public double checkAccountBalance() {
        return accountBalance;
    }

    @Override
    public void setAccountBalance(Double amount) {
        accountBalance += amount;
    }
    @Override
    public boolean isAvailable(List<Product> customerCart) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // checks that store is not empty, and it contains the required products and their quantities;
        boolean result;
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " wants to check if some products are available..");
            result = customerCart.stream().allMatch(product -> !storeCatalogue.isEmpty()
                    && storeCatalogue.containsKey(product.getName()) && storeCatalogue.get(product.getName()).getQuantity() >= product.getQuantity());
        }
        if (!result) {
            System.out.println(Thread.currentThread().getName() + " the items you ordered are currently out of stock >>>");
        } else {
            System.out.println(Thread.currentThread().getName() + " is done checking if items they wish to order are available...");
        }
        return result;
    }
    @Override
    public void sellProducts(List<Product> customerCart) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            System.out.println("########### ***********<<>>*********** ###########");
            System.out.println(Thread.currentThread().getName() + " is making purchase... >>>");
            double total = 0.0;
            for (Product product : customerCart) {
                if (!storeCatalogue.isEmpty() && storeCatalogue.containsKey(product.getName())
                        && storeCatalogue.get(product.getName()).getQuantity() >= product.getQuantity()) {
                    // updates each item in the customer's cart from the corresponding product in the store catalogue.
                    storeCatalogue.get(product.getName()).setQuantity(storeCatalogue.get(product.getName()).getQuantity() - product.getQuantity());
                    total += product.getQuantity() * product.getPrice();
                    String message = String.format("Sold %d units of %s", product.getQuantity(), product.getName());
                    System.out.println(message);
                }
            }
            setAccountBalance(total);
            System.out.println("Credit alert: $" + total + " from " + Thread.currentThread().getName());
            System.out.println("Company account updated Balance: $" + accountBalance);
        }
        System.out.println(Thread.currentThread().getName() + " is done purchasing >>>");
        String message = String.format("%s your orders will be delivered soon...", Thread.currentThread().getName());
        System.out.println(message);
        System.out.println("########### ***********<<>>*********** ###########");
    }
}
