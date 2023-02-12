package com.shopwell.services.servicesimplementation;

import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.InventoryService;

public class InventoryServiceImpl implements InventoryService {
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
}
