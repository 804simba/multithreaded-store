package com.shopwell.services.servicesimplementation;

import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.IInventoryService;

public class InventoryServiceImpl implements IInventoryService {
    Store store;
    Product product;
    @Override
    public void updateProductQuantityInExcel() {
//        store.updateProductQtyInExcel();
    }

    @Override
    public void readProductQuantityInExcel() {
        store.ReadAllProductsInExcelSheet();
    }

    @Override
    public void addProductToInventory(Product product) {
        store.addProductToExcel(product);
    }
}
