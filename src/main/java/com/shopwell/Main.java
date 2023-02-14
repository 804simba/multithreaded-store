package com.shopwell;

import com.shopwell.staff.ExcelManager;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("shopwell", 100000);

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 11);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 10);

        Product soap = new Product("Soap", 100, PRODUCTCATEGORY.TOILETRIES, 30);

        store.ReadAllProductsInExcelSheet();

        ExcelManager excelManager = new ExcelManager();
//        excelManager.reduceProductQuantity(rice, 1);
//        excelManager.reduceProductQuantity(beer, 1);
//        store.ReadAllProductsInExcelSheet();

//        excelManager.addProductToInventory(soap);
//        store.ReadAllProductsInExcelSheet();

    }
}