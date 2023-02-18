package com.shopwell.staff;

import com.shopwell.Store;
import com.shopwell.enums.Role;
import lombok.Getter;

@Getter
public abstract class Staff {
    private final String name;
    private int StaffID = 1400;
    private final Role role;
    protected Store store;

    public Staff(String name, Role role, Store store) {
        this.name = name;
        this.role = role;
        this.store = store;
        StaffID++;
    }
}
