package com.shopwell;

import com.shopwell.customers.Customer;
import com.shopwell.products.Product;
import com.shopwell.services.IAccountableManager;
import com.shopwell.services.IEmployeeManager;
import com.shopwell.services.IExcelService;
import com.shopwell.services.IQueueManager;
import com.shopwell.staff.Cashier;
import com.shopwell.utilities.CartSizeComparator;
import com.shopwell.utilities.ExcelManager;
import com.shopwell.staff.Manager;
import lombok.Getter;

import java.util.*;

@Getter
public class Store implements IAccountableManager, IEmployeeManager, IExcelService {
    private final String name;
    private Double accountBalance;
    private Double dailySalesAccount = 0.0;
    private final List<Cashier> cashiersList = new ArrayList<>();
    private final List<Product> productsList = new ArrayList<>();
    private ExcelManager excelManager;

    public Store(String name, Double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        try {
            this.excelManager = new ExcelManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public double checkAccountBalance(Object other) {
        if (other instanceof Manager) {
            return accountBalance;
        }
        return 0.0;
    }
    @Override
    public void setDailySalesAccount(Double amount, Object other) {
        if (other instanceof Cashier) {
            dailySalesAccount += amount;
            return;
        }
        System.out.println("You don't have access to update this account.");
    }
    @Override
    public void updateStoreAccountBalance(Double totalDailySales, Object other) {
        if (other instanceof Manager)
            accountBalance += totalDailySales;
    }
    @Override
    public void addCashier(Cashier cashier, Object other) {
        if (other instanceof Manager)
            cashiersList.add(cashier);
    }
    @Override
    public void addProducts(Product product, Object other) {
        if (other instanceof Manager)
            productsList.add(product);
    }
    @Override
    public boolean isAvailable(Product product) {
        for (Product storeProduct : getProductsList()) {
            if (storeProduct.getProductName().equals(product.getProductName()) && storeProduct.getProductQuantity() >= product.getProductQuantity())
                return true;
        }
        return false;
    }

    public int ReadAllProductsInExcelSheet() {
        try {
            return excelManager.printAllDataFromExcel();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public void addProductToExcel(Product product) {
        excelManager.addProductToInventory(product);
    }

    public void updateProductQtyInExcel(Product product, int quantity) {
        excelManager.updateProductQuantity(product, quantity);
    }
    @Override
    public String toString() {
        return "Store{" +
                "productsList=" + productsList +
                '}';
    }
}
