package com.shark.example.shoalter.patch;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class WeightPatch {


    public static void main(String[] argv) {
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(new File("mmsFile/patch/Production_weight_Patch_20230630.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            int index = 0;
            StringBuilder sqlStringBuilder = new StringBuilder("use mms;");
            sqlStringBuilder.append("\n");
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(index > 1) {
                    Cell idCell = row.getCell(0);
                    int id = (int) idCell.getNumericCellValue();
                    Cell weightCell = row.getCell(17);
                    int weight = (int) weightCell.getNumericCellValue();
//                    System.out.println("id: " + id + ", weight: " + weight);
                    sqlStringBuilder.append("update PRODUCT set WEIGHT = ")
                            .append(weight)
                            .append(" where ID = ")
                            .append(id)
                            .append(";").append("\n");
                }
                index ++;
            }
            System.out.println(sqlStringBuilder);
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
