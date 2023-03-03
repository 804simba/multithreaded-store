package com.shopwell.services.implementations;

import com.shopwell.product.Product;
import com.shopwell.services.ICatalogueService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogueServiceImpl implements ICatalogueService {
    @Override
    public void buyProducts(List<Product> customerCart) {

    }
    @Override
    public boolean isAvailable(List<Product> customerCart) {
        return false;
    }
}
