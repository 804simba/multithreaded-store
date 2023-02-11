package com.shopwell.staff;

import com.shopwell.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class Staff {
    private String name;
    private int StaffID = 1400;
    private Designation designation;
    protected Store store;

    public Staff(String name, Designation designation, Store store) {
        this.name = name;
        this.designation = designation;
        this.store = store;
        StaffID++;
    }
}
