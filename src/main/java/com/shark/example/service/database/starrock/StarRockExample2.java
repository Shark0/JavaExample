package com.shark.example.service.database.starrock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StarRockExample2 {
    public static void main(String[] argv) {
        String url = "jdbc:mysql://localhost:19030/example?rewriteBatchedStatements=true";
        String user = "root";
        String password = "";
        String insertSql = "INSERT INTO chatroom (user_id, user_service, item_id, item_service, time)\n" +
                "SELECT d*1000+c*100+b*10+a, 'HKTV', 1, 'CUSTOMER_CHAT', now()\n" +
                "FROM\n" +
                "(select 0 a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t1," +
                "(select 0 b union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t2," +
                "(select 0 c union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t3," +
                "(select 0 d union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t4";

        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            long startTime = System.currentTimeMillis();
                preparedStatement.execute();
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.println("total time = " + totalTime);
//                System.out.println("result.length: " + result.length + ", result: " + Arrays.toString(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
