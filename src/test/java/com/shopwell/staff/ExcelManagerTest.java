package com.shopwell.staff;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ExcelManagerTest {
    ExcelManager exm;
    @BeforeAll
    void init() {
        exm = new ExcelManager();
    }

    @Test
    void shouldReadProductNameInThirdRow() {
        exm.
    }
}