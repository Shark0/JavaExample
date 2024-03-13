package com.shark.example.jdbc;

import java.sql.*;

public class JdbcExample {
    public static void main(String[] argv) {
        String url = "jdbc:mysql://localhost:3306/group_chat_hktv?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&connectionTimezone=UTC&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "group_chat";
        String password = "group_chat";

        String query = "select UP.USER_ID " +
                "from CHATROOM_USER CU " +
                "inner join USER_PROFILE UP on CU.USER_ID = UP.USER_ID " +
                "where CU.CHATROOM_ID = 75 and UP.ONLINE_STATUS = 1";

        long startTime = System.currentTimeMillis();
        int dataCount = 0;
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            // Extract data from result set
            while (resultSet.next()) {
                // Retrieve by column name
//                System.out.print("ID: " + resultSet.getString("USER_ID"));
                dataCount ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime);
        System.out.println("total time = " + totalTime);
        System.out.println("data count = " + dataCount);
    }
}
