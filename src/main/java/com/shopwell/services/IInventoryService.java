package com.shopwell.services;

import com.shopwell.Product;

public interface IInventoryService {
    void updateProductQuantityInExcel(Product product, int quantity);
    void readProductsInExcel();
    void addProductToInventoryInExcel(Product product);
}
