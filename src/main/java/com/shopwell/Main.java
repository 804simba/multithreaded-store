package com.shopwell;

import com.shopwell.staff.ExcelManager;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("shopwell", 100000);

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 11);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 10);
//
        Product soap = new Product("Soap", 100, PRODUCTCATEGORY.TOILETRIES, 30);
        Product perfume = new Product("Dior Perfume", 10, PRODUCTCATEGORY.TOILETRIES, 20);


//        ExcelManager excelManager = new ExcelManager();

//        store.ReadAllProductsInExcelSheet();
//        store.getExcelManager().addProductToInventory(perfume);
        store.getExcelManager().reduceProductQuantity(rice, 270);
        store.ReadAllProductsInExcelSheet();
        store.getExcelManager().addProductToInventory(beer);
        store.ReadAllProductsInExcelSheet();

    }
}