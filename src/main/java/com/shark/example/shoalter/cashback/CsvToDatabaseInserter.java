package com.shark.example.shoalter.cashback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class CsvToDatabaseInserter {
    // 資料庫連線資訊 - 請根據您的環境修改
    private static final String DB_URL = "jdbc:mysql://localhost:3307/cashback?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // CSV 檔案路徑
    private static final String CSV_FILE_PATH = "file/excel/Australian-Network.csv";

    // INSERT SQL - 根據 CREATE TABLE 調整，63 個欄位，使用 ? 佔位符
    private static final String INSERT_SQL =
            "INSERT INTO australian_network (" +
                    "`Advertiser Name`, `Gross Sales`, `Member ID (U1)`, `MID`, `Offer ID`, " +
                    "`Offer Name`, `Offer Number`, `Order ID`, `Order Coupon Code(s)`, `In-Store ID`, " +
                    "`Merchant Store ID`, `Conversion Type`, `Check In Date`, `Stay Length`, `Check Out Date`, " +
                    "`Reservation Start Date`, `Reservation End Date`, `Booking Consumed Date`, `Product ID`, `Product Category ID`, " +
                    "`Product Category ID 2`, `Product Category ID 3`, `Product Category ID 4`, `Product Category ID 5`, `Product Category`, " +
                    "`Product Category 2`, `Product Category 3`, `Product Category 4`, `Vehicle Type`, `Property Type`, " +
                    "`Accommodation Type`, `Travel Type`, `Process Date`, `Product Name`, `Sales`, " +
                    "`SKU`, `Transaction Date`, `Transaction ID`, `Offer Rule`, `Adjusted Commission`, " +
                    "`Baseline Commission`, `Browser`, `Commission Status`, `Consumer City`, `Device`, " +
                    "`Group Offer ID`, `Gross Commissions`, `Gross Total Commissions`, `Commissioning List ID`, `Commissioning List Name`, " +
                    "`Mobile Operating System`, `Non-Commissionable Items`, `Non-Commissionable Order ID`, `Non-Commissionable Reason`, `Non-Commissionable Sales`, " +
                    "`Offer Group ID`, `Order Discount Amount`, `Order Status`, `Sale Flag`, `Total Commission`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        BufferedReader reader = null;
        int insertedRows = 0;

        try {
            // 載入 JDBC Driver (MySQL 8.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = connection.prepareStatement(INSERT_SQL);

            // 讀取 CSV - 跳過標頭行
            reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String line;
            reader.readLine(); // 跳過 CSV 標頭

            // 一行一行讀取並插入
            while ((line = reader.readLine()) != null) {
                System.out.println("insertedRows: " + insertedRows);
                String[] values = parseCsvLine(line); // 解析 CSV 行（處理逗號和引號）
                System.out.println("values.length: " + values.length);
                if (values.length != 60) { // 檢查欄位數
                    System.err.println("警告：行有 " + values.length + " 欄位，跳過");
                    continue;
                }

                // 設定 PreparedStatement 參數
                setStatementParameters(pstmt, values);
                pstmt.executeUpdate();
                insertedRows++;
            }

            System.out.println("插入完成！總共 " + insertedRows + " 行。");

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
                if (reader != null) reader.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] parseCsvLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // 正則：逗號後跟偶數引號（處理引號內逗號）
    }

    private static void setStatementParameters(PreparedStatement preparedStatement, String[] values) throws SQLException {
        for (int i = 0; i < 60; i++) {
            String value = values[i].trim();
            System.out.println("i: " + i + ", value: " + value);
            if (value.isEmpty() || "nan".equalsIgnoreCase(value) || "null".equalsIgnoreCase(value)) {
                preparedStatement.setNull(i + 1, java.sql.Types.VARCHAR); // 預設 VARCHAR NULL
            } else {
                preparedStatement.setString(i + 1, value);
            }
        }
    }
}