package com.shopwell.staff;

import com.shopwell.PRODUCTCATEGORY;
import com.shopwell.Product;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Getter
// https://poi.apache.org/components/spreadsheet/quick-guide.html#FileInputStream
public class ExcelManager {
    private static final String FILE_PATH = "src/main/java/com/shopwell/shopwell-inventory.xlsx";
    public ExcelManager() {
    }

    public void addProductToInventory(Product product){
        Object[] productDetailsArray = new Object[4];
        productDetailsArray[0] = product.getProductName();
        productDetailsArray[1] = product.getProductPrice();
        productDetailsArray[2] = product.getProductQuantity();
        productDetailsArray[3] = product.getProductCategory();
        List<Object[]> productDetails = new ArrayList<>();
        productDetails.add(productDetailsArray);

        try (FileInputStream inputStream = new FileInputStream(FILE_PATH);) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            for (Object[] objArray : productDetails) {
                Row row = sheet.createRow(++rowCount);
                row.setRowNum(rowCount++);
                int columnCount = 0;
                for (Object obj : objArray) {
                    Cell cell = row.createCell(columnCount++);
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                    }
                    if (obj instanceof Integer) {
                        cell.setCellValue((Integer) obj);
                    }
                    if (obj instanceof Double) {
                        cell.setCellValue((Double) obj);
                    }
                    if (obj instanceof PRODUCTCATEGORY) {
                        cell.setCellValue(obj.toString());
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream(FILE_PATH);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Updated successfully...");
    }

    // https://www.codejava.net/coding/java-example-to-update-existing-excel-files-using-apache-poi

    public void reduceProductQuantity(Product product, int quantity) {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH);) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            FileOutputStream outputStream = new FileOutputStream(FILE_PATH);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            // loop through each row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // check the first cell of each row, for product name that matches.
                if (row.getCell(0).getStringCellValue().equals(product.getProductName())) {
                    // get the current quantity.
                    int currentQuantity = (int) row.getCell(2).getNumericCellValue();
                    if (currentQuantity >= quantity) {
                        // update the current quantity.
                        int newQuantity = currentQuantity - quantity;
                        row.getCell(2).setCellValue(newQuantity);
                        workbook.write(outputStream);
                        System.out.printf("You've updated %s, New Qty: %d\n", product.getProductName(), newQuantity);
                    } else {
                        System.out.println("Sorry we don't have enough units of this product.");
                    }
                    return;
                }
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printAllDataFromExcel() {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH);) {
            // create inputStream
            // create workBook instance
            Workbook workbook = WorkbookFactory.create(inputStream);
            // get first sheet from workbook
            Sheet sheet = workbook.getSheetAt(0);

            // loop through each row on sheet
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                // for each row, loop through the cells
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    // get cell type and print value
                    switch (currentCell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.printf("%13s", currentCell.getStringCellValue());
                            System.out.print("   ");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.printf("%13s", currentCell.getNumericCellValue());
                            System.out.print("   ");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
