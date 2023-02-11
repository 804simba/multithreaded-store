package com.shopwell.staff;

import com.shopwell.Store;
import lombok.Getter;

import java.util.Iterator;

@Getter
public class Manager extends Staff {
    public Manager(String name, Designation designation, Store store) {
        super(name, designation, store);
    }

    public void fireStaff(Cashier cashier) {
        Iterator<Cashier> i = store.getCashiersList().iterator();
        while (i.hasNext()) {
            Cashier c = i.next();
            if (c.getName() == cashier.getName() && cashier.getEmploymentStatus() == true) {
                setEmploymentStatus(cashier);
                store.getCashiersList().remove(cashier);
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
        System.out.printf("You hired %s\n", cashier.getName());
    }

    public void addSalesToCompanyAccount(double totalSalesForDay) {
        store.updateStoreAccountBalance(totalSalesForDay, this);
        System.out.println();
        System.out.printf("Total Sales for the day: %.2f\n", totalSalesForDay);
        System.out.printf("Current company Balance: %.2f\n", store.checkAccountBalance(this));
    }
}
