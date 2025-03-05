package com.shark.example.service.database.clickhouse;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Random;

public class ClickHouseExample {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:ch://localhost:8123/default");
        config.setUsername("default");
        config.setPassword("changeme");
        config.setMaximumPoolSize(10);

        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            int messageCount = 10;
            for(int i = 1; i <= messageCount; i++){
                int userIdOffset = new Random().nextInt(10);
                int messageIdOffset = new Random().nextInt(10000);
                Connection connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("insert into message_insert(user_id, user_service, item_id, item_service, message_id, time) values (?, ?, ?, ?, ?, ?)");
                int userCount = 100000;
                long start = System.currentTimeMillis();
                for (int j = 1; j <= userCount; j++) {
                    preparedStatement.setInt(1, j + userIdOffset);
                    preparedStatement.setString(2, "HKTV");
                    preparedStatement.setInt(3, 1);
                    preparedStatement.setString(4, "HSS");
                    preparedStatement.setInt(5, i + messageIdOffset);
                    preparedStatement.setDate(6, new Date(System.currentTimeMillis()));
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
                connection.commit();
                long end = System.currentTimeMillis();
                System.out.println("total time: " + (end - start));

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
