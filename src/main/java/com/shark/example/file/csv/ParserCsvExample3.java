package com.shark.example.file.csv;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.shark.example.file.csv.pojo.RakutenReportDo;
import com.shark.example.file.txt.ReadTxtFileExample;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParserCsvExample3 {

    public static void main(String[] argv) {
        String txt = new ReadTxtFileExample().readFile("file/csv_example.txt");
        List<RakutenReportDo> list = new ArrayList<>();
        int index = 0;
        try (
            CSVReader csvReader = new CSVReader(new StringReader(txt));
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if(index > 4) {
                    RakutenReportDo rakutenReportDo = getRakutenReportDo(nextRecord);
                    list.add(rakutenReportDo);
                }
                index ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new Gson().toJson(list));
    }

    private static RakutenReportDo getRakutenReportDo(String[] record)
    {
        RakutenReportDo rakutenReportDo = new RakutenReportDo();
        rakutenReportDo.setMemberId(record[0]);
        rakutenReportDo.setMID(record[1]);
        rakutenReportDo.setAdvertiserName(record[2]);
        rakutenReportDo.setOrderId(record[3]);
        rakutenReportDo.setTransactionDate(record[4]);
        rakutenReportDo.setTransactionTime(record[5]);
        rakutenReportDo.setSku(record[6]);
        rakutenReportDo.setSales(record[7]);
        rakutenReportDo.setItems(record[8]);
        rakutenReportDo.setTotalCommission(record[9]);
        rakutenReportDo.setProcessDate(record[10]);
        rakutenReportDo.setProcessTime(record[11]);
        return rakutenReportDo;
    }


}
