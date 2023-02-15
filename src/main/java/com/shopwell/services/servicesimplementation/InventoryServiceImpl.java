package com.shopwell.services.servicesimplementation;

import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.IInventoryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InventoryServiceImpl implements IInventoryService {
    Store store;
    @Override
    public void updateProductQuantityInExcel(Product product, int quantity) {
        store.updateProductQtyInExcel(product, quantity);
    }

    @Override
    public void readProductsInExcel() {
        store.ReadAllProductsInExcelSheet();
    }

    public void addProductToInventoryInExcel(Product product) {
        store.addProductToExcel(product);
    }
}
