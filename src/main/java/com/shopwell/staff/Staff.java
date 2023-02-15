package com.shopwell.staff;

import com.shopwell.Store;
import lombok.Getter;

@Getter
public abstract class Staff {
    private String name;
    private int StaffID = 1400;
    private Role role;
    protected Store store;

    public Staff(String name, Role role, Store store) {
        this.name = name;
        this.role = role;
        this.store = store;
        StaffID++;
    }
}
