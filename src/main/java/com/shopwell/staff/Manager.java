package com.shopwell.staff;

import com.shopwell.Store;
import lombok.Getter;

import java.util.Iterator;

@Getter
public class Manager extends Staff {
    public Manager(String name, Role role, Store store) {
        super(name, role, store);
    }

    public void fireStaff(Cashier cashier) {
        Iterator<Cashier> i = store.getCashiersList().iterator();
        while (i.hasNext()) {
            Cashier c = i.next();
            if (c.getName().equals(cashier.getName()) && cashier.getEmploymentStatus()) {
                cashier.setEmploymentStatus(false);
                i.remove();
                System.out.printf("You fired %s", cashier.getName());
                return;
            }
        }
    }

    public void setEmploymentStatus(Cashier cashier) {
        if (cashier.getEmploymentStatus())
            cashier.setEmploymentStatus(false);
    }
    public void hireStaff(Cashier cashier) {
        store.addCashier(cashier, this);
        cashier.setEmploymentStatus(true);
        System.out.printf("You hired %s\n", cashier.getName());
    }

    public void addSalesToCompanyAccount(double totalSalesForDay) {
        store.updateStoreAccountBalance(totalSalesForDay, this);
        System.out.println();
        System.out.printf("Total Sales for the day: %.2f\n", totalSalesForDay);
        System.out.printf("Current company Balance: %.2f\n", store.checkAccountBalance(this));
    }
}
