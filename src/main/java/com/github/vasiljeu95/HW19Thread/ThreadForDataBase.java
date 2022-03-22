package com.github.vasiljeu95.HW19Thread;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ThreadForDataBase extends Thread {
    public static final String URL = "jdbc:mysql://localhost:3306/plant";
    public static final String USER = "root";
    public static final String PASSWORD = "root1234";

    public ThreadForDataBase(String name) {
        super(name);
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is started!");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("База данных успешно подключена!");

            Statement statement = connection.createStatement();

            String sqlRequestFirst = "SELECT * FROM employee WHERE department_id IS NULL";
            JDBC.resultSQLRequest(statement, sqlRequestFirst);

            String sqlRequestSecond = "SELECT * FROM employee WHERE salary>(SELECT avg(salary) FROM employee)";
            JDBC.resultSQLRequest(statement, sqlRequestSecond);

            statement.close();
        } catch (SQLException exception) {
            System.out.println("База данных подключена не успешно!");
            exception.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is finished!");
    }
}
