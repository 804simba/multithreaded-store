package com.shopwell.utilities;

import com.shopwell.enums.PRODUCTCATEGORY;
import com.shopwell.products.Product;
import com.shopwell.utilities.ExcelManager;
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
        double expected = 205.0;
        int actual = exm.updateProductQuantity(rice, 5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetTheTotalNumberOfItems() {
        int expected = 4;
        int actual = exm.printAllDataFromExcel();
        assertEquals(expected, actual);
        assertNotEquals((Integer) null, actual);
    }
}