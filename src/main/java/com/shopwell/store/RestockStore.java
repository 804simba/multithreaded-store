package com.shopwell.store;

import com.shopwell.enums.ProductCategory;
import com.shopwell.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RestockStore implements Runnable{
    Store store;
    Map<String, Product> newProductsMap;
    public RestockStore(Store store, Map<String, Product> newProducts) {
        this.store = store;
        this.newProductsMap = newProducts;
    }
    private void restockStore() {
        for (Product newProduct : newProductsMap.values()) {
            Product existingProduct = store.getStoreCatalogue().get(newProduct.getName());
            if (existingProduct != null) {
                existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
            } else {
                store.getStoreCatalogue().put(newProduct.getName(), newProduct);
            }
        }
        System.out.println("Restocked products catalogue: " + store.getStoreCatalogue().toString());
    }
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        restockStore();
        System.out.println(">>>>>>>> ADDED NEW PRODUCTS TO STORE...");
    }
    public void startRestockThread() {
        Thread restockThread = new Thread(this);
        restockThread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Restocking...");
    }
}
