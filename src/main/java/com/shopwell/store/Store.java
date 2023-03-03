package com.shopwell.store;

import com.shopwell.product.Product;
import com.shopwell.services.IAccountManager;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Store implements IAccountManager {
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
}
