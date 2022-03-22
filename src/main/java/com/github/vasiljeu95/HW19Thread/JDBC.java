package com.github.vasiljeu95.HW19Thread;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class JDBC {

    public static void resultSQLRequest(Statement statement, String sqlRequestSecond) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sqlRequestSecond);

        try (FileWriter writer = new FileWriter("requestSQLInNewThread.txt", true)) {
            String resultText = "";

            while (resultSet.next()) {
                long id = resultSet.getLong("client_id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                int department_id = resultSet.getInt("department_id");

                resultText = String.format("id: %d, name: %s, salary: %s, department_id: %s\n", id, name, salary, department_id);
                writer.write(resultText);
            }
        } catch (IOException exception) {
            System.out.println("File write is error!");
            exception.printStackTrace();
        }

        resultSet.close();
    }
}