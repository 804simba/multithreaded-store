package com.shopwell.services.implementations;

import com.shopwell.product.Product;
import com.shopwell.services.ICatalogueService;

import java.util.List;

public class CatalogueServiceImpl implements ICatalogueService {
    @Override
    public void addProductToStock(String name, Product product) {

    }

    @Override
    public void buyProducts(List<Product> customerCart) {

    }

    @Override
    public boolean isAvailable(List<Product> customerCart) {
        return false;
    }
}
