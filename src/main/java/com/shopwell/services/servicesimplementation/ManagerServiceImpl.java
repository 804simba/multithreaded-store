package com.shopwell.services.servicesimplementation;

import com.shopwell.Product;
import com.shopwell.Store;
import com.shopwell.services.IManagerService;
import com.shopwell.staff.Cashier;
import com.shopwell.staff.Manager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ManagerServiceImpl implements IManagerService {
    Manager manager;
    Store store;

    @Override
    public void fireStaff(Cashier cashier) {
        manager.fireStaff(cashier);
    }

    @Override
    public void setEmploymentStatus(Cashier cashier) {
        manager.setEmploymentStatus(cashier);
    }

    @Override
    public void hireStaff(Cashier cashier) {
        manager.hireStaff(cashier);
    }

    @Override
    public void addSalesToCompanyAccount(double totalSalesForDay) {
        manager.addSalesToCompanyAccount(totalSalesForDay);
    }

    @Override
    public void addProduct(Product product) {
        store.addProducts(product, this.manager);
    }

    @Override
    public String getProductList() {
        return store.getProductsList().toString();
    }
}
