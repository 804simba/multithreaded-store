package com.shopwell.staff;

import com.shopwell.PRODUCTCATEGORY;
import com.shopwell.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelManagerTest {
    ExcelManager exm;
    Product rice;
    @BeforeEach
    public void init() {
        exm = new ExcelManager();
        rice = new Product("Rice", 50000, PRODUCTCATEGORY.GROCERIES, 100);
    }

    @Test
    void shouldGetTheRemainingProductQuantity() {
        double expected = 230.0;
        int actual = exm.updateProductQuantity(rice, 10);
        assertEquals(expected, actual);
    }
}