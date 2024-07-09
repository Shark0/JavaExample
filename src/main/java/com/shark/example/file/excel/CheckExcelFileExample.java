package com.shark.example.file.excel;

import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class CheckExcelFileExample {

    public static void main(String[] argv) {
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream("file/building_materials.xlsx"));
            if(workbook == null) {
                System.out.println("building_materials.xlsx is not excel");
            } else {
                System.out.println("building_materials.xlsx is excel");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream("file/building_materials.numbers"));
            if(workbook == null) {
                System.out.println("building_materials.number is not excel");
            } else {
                System.out.println("building_materials.number is excel");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileMagic fm = FileMagic.valueOf(FileMagic.prepareToCheckMagic(new FileInputStream("file/xls.xls")));
            switch(fm) {
                case OLE2:
                    System.out.println("OLE2");
                    break;
                case OOXML:
                    System.out.println("OOXML");
                    break;
                default:
                    System.out.println("IS NOT EXCEL");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileMagic fileMagic = FileMagic.valueOf(FileMagic.prepareToCheckMagic(new FileInputStream("file/time_error.xlsx")));
            switch(fileMagic) {
                case OLE2:
                    System.out.println("OLE2");
                    break;
                case OOXML:
                    System.out.println("OOXML");
                    break;
                default:
                    System.out.println("IS NOT EXCEL");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
