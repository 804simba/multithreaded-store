package com.shopwell.utilities;

import com.shopwell.enums.PRODUCTCATEGORY;
import com.shopwell.products.Product;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Getter
public class ExcelManager {
    private static final String FILE_PATH = "src/main/resources/shopwell-inventory.xlsx";
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

    public int updateProductQuantity(Product product, int quantity) {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH);) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0).getStringCellValue().equals(product.getProductName())) {
                    int currentQuantity = (int) row.getCell(2).getNumericCellValue();
                    if (currentQuantity >= quantity) {
                        int newQuantity = currentQuantity - quantity;
                        row.getCell(2).setCellValue(newQuantity);
                        FileOutputStream outputStream = new FileOutputStream(FILE_PATH);
                        workbook.write(outputStream);
                        outputStream.close();
                        System.out.printf("You have %d units of %s left\n", newQuantity, product.getProductName());
                        return newQuantity;
                    } else {
                        System.out.printf("Sorry we don't have enough units of %s.\n", product.getProductName());
                        System.out.println();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public int printAllDataFromExcel() {
        try (FileInputStream inputStream = new FileInputStream(FILE_PATH);) {
            System.out.print("#################*****-- STORE INVENTORY --*****#################\n");
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

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
            System.out.print("#################  **********--  --**********  #################\n");
            return sheet.getLastRowNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
