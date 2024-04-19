package com.shark.example.file.csv;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.mozilla.universalchardet.UniversalDetector;
import com.shark.example.data.string.StringUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;

public class ParserCsvExample {
    public static void main(String argv[]) throws IOException {
        long startTime = System.currentTimeMillis();
        File file = findFile("file/test.csv");
        String encode = detectFileEncode(file);
        FileReader fileReader = new FileReader("file/test.csv", Charset.forName(encode));
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
        Gson gson = new Gson();
        HashSet<Integer> cellSizeSet = new HashSet<>();
        while ((nextRecord = csvReader.readNext()) != null) {
//            System.out.println("cell size: " + nextRecord.length + ", data: "+ gson.toJson(nextRecord));
            cellSizeSet.add(nextRecord.length);
        }
//        System.out.println("cell length count: " + cellSizeSet.size() + ", data: " + gson.toJson(cellSizeSet));
        csvReader.close();
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
    }

    private static File findFile(String path) {
        return new File(path);
    }

    private static String detectFileEncode(File file) {
        byte[] buff = new byte[4096];
        String encode = null;
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            UniversalDetector detector = new UniversalDetector(null);
            int read;
            while ((read = fileInputStream.read(buff)) > 0 && !detector.isDone()) {
                detector.handleData(buff, 0, read);
            }
            detector.dataEnd();
            encode = detector.getDetectedCharset();
            if (StringUtil.isEmpty(encode)) {
                encode = "UTF-8";
            }
            detector.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("encode: " + encode);
        return encode;
    }
}
