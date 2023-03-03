package com.shopwell.store;

import com.shopwell.product.Product;
import com.shopwell.services.IAccountManager;
import com.shopwell.services.ICatalogueService;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Store implements IAccountManager {
    private final String name;
    private Double accountBalance = 0.0;
    private final ICatalogueService iCatalogueService;
    final private Map<String, Product> storeCatalogue;

    public Store(String name, Double accountBalance, ICatalogueService iCatalogueService) {
        this.name = name;
        this.accountBalance = accountBalance;
        this.storeCatalogue = new ConcurrentHashMap<>();
        this.iCatalogueService = iCatalogueService;
    }
    @Override
    public double checkAccountBalance() {
        return accountBalance;
    }

    @Override
    public void setDailySalesAccount(Double amount) {
        accountBalance += amount;
    }
    public boolean isAvailable(List<Product> customerCart) {
        return iCatalogueService.isAvailable(customerCart);
    }

    public void buyProducts(List<Product> customerCart) {
        iCatalogueService.buyProducts(customerCart);
    }
    public void addProductToStock(String name, Product product) {
        iCatalogueService.addProductToStock(name, product);
    }
}
