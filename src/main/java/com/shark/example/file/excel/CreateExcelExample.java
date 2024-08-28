package com.shark.example.file.excel;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcelExample {

    private static final String FILE_PATH = "file/excel_example.xlsx";


    public static void main(String[] argv) {
        createWorkBook();
    }

    private static void createWorkBook() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet");
        String[] columns = {"number", "square", "menu"};
        String[] menu = {"M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10"};
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(menu);
        CellRangeAddressList addressList = new CellRangeAddressList(1, 1, 2, 2);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        int rowIndex = 1;
        for (int i = 0; i < 20; i++) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(String.valueOf(i));
            row.createCell(1).setCellValue(String.valueOf(i * i));
            rowIndex = rowIndex + 1;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
