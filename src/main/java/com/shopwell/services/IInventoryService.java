package com.shopwell.services;

import com.shopwell.Product;

public interface IInventoryService {
    void updateProductQuantityInExcel();
    void readProductQuantityInExcel();
    void addProductToInventory(Product product);
}
