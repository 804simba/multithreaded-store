package com.shopwell.services;

import com.shopwell.Product;
import com.shopwell.staff.Cashier;

public interface ManagerService {
    void addProduct(Product product);
    String getProductList();
    void fireStaff(Cashier cashier);
    void hireStaff(Cashier cashier);
    void addSalesToCompanyAccount(double totalSalesForDay);
    void setEmploymentStatus(Cashier cashier);
}
