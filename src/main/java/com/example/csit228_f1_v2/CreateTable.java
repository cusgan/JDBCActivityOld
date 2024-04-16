package com.example.csit228_f1_v2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        Connection c = MySQLConnection.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS appusers (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(50) not null, " +
                "password VARCHAR(50) not null)";
        try {
            Statement statement = c.createStatement();
            statement.execute(query);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
