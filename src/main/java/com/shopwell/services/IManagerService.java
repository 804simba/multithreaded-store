package com.shopwell.services;

import com.shopwell.products.Product;
import com.shopwell.staff.Cashier;

public interface IManagerService {
    void addProduct(Product product);
    String getProductListDetail();
    void fireStaff(Cashier cashier);
    void hireStaff(Cashier cashier);
    void addSalesToCompanyAccount(double dailySales);
    void setEmploymentStatus(Cashier cashier);
}
