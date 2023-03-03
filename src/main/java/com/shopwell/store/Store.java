package com.shopwell.store;

import com.shopwell.product.Product;
import com.shopwell.services.IAccountManager;
import com.shopwell.services.ICatalogueService;
import lombok.Getter;

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
    public double checkAccountBalance() {
        return accountBalance;
    }

    @Override
    public void setDailySalesAccount(Double amount) {
        accountBalance += amount;
    }
    @Override
    public boolean isAvailable(List<Product> customerCart) {
        System.out.println("Checking if products are available..");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // checks that store is not empty, and it contains the required products and their quantities;
        boolean result = customerCart.stream().allMatch(product -> !storeCatalogue.isEmpty()
                && storeCatalogue.containsKey(product.getName()) && storeCatalogue.get(product.getName()).getQuantity() >= product.getQuantity());
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " checking if their ordered items are available...");
            if (!result) {
                System.out.println(Thread.currentThread().getName() + " the items you ordered are currently out of stock >>>");
            }
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
            for (Product product : customerCart) {
                if (!storeCatalogue.isEmpty() && storeCatalogue.containsKey(product.getName())
                        && storeCatalogue.get(product.getName()).getQuantity() >= product.getQuantity()) {
                    storeCatalogue.get(product.getName()).setQuantity(storeCatalogue.get(product.getName()).getQuantity() - product.getQuantity());
                    String message = String.format("Sold %d units of %s", product.getQuantity(), product.getName());
                    System.out.println(message);
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " is done purchasing >>>");
        System.out.println("########### ***********<<>>*********** ###########");
    }
    public void addProductToStock(String name, Product product) {

    }
}
