package com.shopwell.staff;

import com.shopwell.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ExcelManager {
    private static final String FILE_PATH = "/Users/decagon/IdeaProjects/ShopWellStore/src/main/java/com/shopwell/shopwell-store-inventory.xlsx";
    List<Product> products;
    public ExcelManager() {
    }
    public void readAllDataFromExcel() throws IOException {
        FileInputStream excelFileStream = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(excelFileStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        while(rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while(cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();

                switch(currentCell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.printf("%13s" ,currentCell.getStringCellValue());
                        System.out.print(" ");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.printf("%13s" ,currentCell.getNumericCellValue());
                        System.out.print("   ");
                        break;
                }
            }
            System.out.println();
        }
        excelFileStream.close();
    }
}
