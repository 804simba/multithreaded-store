package com.shopwell;

import com.shopwell.services.ICashierService;
import com.shopwell.services.IManagerService;
import com.shopwell.services.servicesimplementation.CashierServiceImpl;
import com.shopwell.services.servicesimplementation.ManagerServiceImpl;
import com.shopwell.staff.Cashier;
import com.shopwell.staff.Role;
import com.shopwell.staff.Manager;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("shopwell", 100000);

        Product rice = new Product("Rice", 2000, PRODUCTCATEGORY.GROCERIES, 11);
        Product beer = new Product("Beer", 20, PRODUCTCATEGORY.GROCERIES, 10);
//
        Product soap = new Product("Soap", 100, PRODUCTCATEGORY.TOILETRIES, 30);
        Product perfume = new Product("Dior Perfume", 10, PRODUCTCATEGORY.TOILETRIES, 20);

        Manager manager = new Manager("Jay", Role.MANAGER, store);
        Cashier cashier = new Cashier("Sarah Mac", Role.CASHIER, store);

        IManagerService ms = new ManagerServiceImpl(manager, store);
        ms.hireStaff(cashier);
        ms.fireStaff(cashier);
//        ICashierService cs = new CashierServiceImpl(cashier, store);

//        store.ReadAllProductsInExcelSheet();
//        store.getExcelManager().addProductToInventory(perfume);
//        store.getExcelManager().reduceProductQuantity(rice, 10);
//        store.getExcelManager().updateProductQuantity(beer, 1);
//        System.out.println(store.ReadAllProductsInExcelSheet());
//        store.getExcelManager().addProductToInventory(beer);
//        store.ReadAllProductsInExcelSheet();

    }
}