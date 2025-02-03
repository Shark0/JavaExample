package com.shark.example.service.database.starrock;

import java.sql.*;
import java.util.Arrays;

public class StarRockExample1 {
    public static void main(String[] argv) {
        String url = "jdbc:mysql://localhost:19030/example?rewriteBatchedStatements=true";
        String user = "root";
        String password = "";
        String insertSql = "INSERT INTO chatroom (user_id, user_service, item_id, item_service, time) VALUES (?, ?, ?, ?, ?)";


        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            int dataCount = 10000;
            for (int i = 0; i < dataCount; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "THE_PLACE");
                preparedStatement.setInt(3, 1);
                preparedStatement.setString(4, "CUSTOMER_CHAT");
                preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
                preparedStatement.addBatch();
            }
            long startTime = System.currentTimeMillis();
            int[] result = preparedStatement.executeBatch();
            System.out.println("result.length: " + result.length + ", result: " + Arrays.toString(result));
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.println("total time = " + totalTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
