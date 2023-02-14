package com.shopwell;

import com.shopwell.staff.Cashier;
import com.shopwell.staff.ExcelManager;
import com.shopwell.staff.Manager;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Store {
    private String name;
    private double accountBalance;
    protected double dailySalesAccount = 0;
    private List<Cashier> cashiersList = new LinkedList<>();
    private List<Product> productsList = new LinkedList<>();

    private ExcelManager excelManager;

    public Store(String name,double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        try {
            this.excelManager = new ExcelManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double checkAccountBalance(Object other) {
        if (other instanceof Manager)
            return accountBalance;
        return 0.0;
    }

    public void setDailySalesAccount(double amount, Object other) {
        if (other instanceof Cashier) {
            dailySalesAccount += amount;
            return;
        }
        System.out.println("You don't have access to update this account.");
    }

    public void updateStoreAccountBalance(double totalDailySales, Object other) {
        if (other instanceof Manager)
            accountBalance += totalDailySales;
    }

    public void addCashier(Cashier cashier, Object other) {
        if (other instanceof Manager)
            cashiersList.add(cashier);
    }

    public void addProducts(Product product, Object other) {
        if (other instanceof Manager)
            productsList.add(product);
    }

    public boolean isAvailable(Product product) {
        for (Product storeProduct : getProductsList()) {
            if (storeProduct.getProductName().equals(product.getProductName()) && storeProduct.getProductQuantity() >= product.getProductQuantity())
                return true;
        }
        return false;
    }

    public void ReadAllProductsInExcelSheet(){
        try {
            excelManager.printAllDataFromExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductToExcel(Product product) {
        excelManager.addProductToInventory(product);
    }

    @Override
    public String toString() {
        return "Store{" +
                "productsList=" + productsList +
                '}';
    }
}
