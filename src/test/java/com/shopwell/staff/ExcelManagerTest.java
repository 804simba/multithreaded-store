package com.shopwell.staff;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ExcelManagerTest {
    @BeforeAll
    void init() {
        ExcelManager exm = new ExcelManager();
        try (FileInputStream fis = new FileInputStream();) {

        } catch (Exception e) {

        }
    }

    @Test
    void shouldReadProductNameInThirdRow() {

    }
}