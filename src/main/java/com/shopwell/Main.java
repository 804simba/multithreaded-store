package com.shopwell;

import com.shopwell.services.CashierService;
import com.shopwell.services.CustomerService;
import com.shopwell.services.ManagerService;
import com.shopwell.services.servicesimplementation.CashierServiceImpl;
import com.shopwell.services.servicesimplementation.CustomerServiceImpl;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import com.shopwell.staff.Cashier;
import com.shopwell.staff.Designation;
import com.shopwell.staff.ExcelManager;
import com.shopwell.staff.Manager;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("shopwell", 100000);

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 11);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 10);

        store.ReadAllProductsInExcelSheet();

        ExcelManager excelManager = new ExcelManager();
        excelManager.updateProductQuantity(rice, 1);
        excelManager.updateProductQuantity(beer, 4);
    }
}