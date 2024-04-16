package com.example.csit228_f1_v2;

import java.sql.*;

public class CRUD {

    //CREATE
    public void insertData(String username, String password) {
        try (
                Connection c = MySQLConnection.getConnection(); /*automatically close()*/
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO appusers (username, password) VALUES (?, ?)"
                );
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows Inserted: " + rowsInserted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //READ
    public void readData() {
        try (
                Connection c = MySQLConnection.getConnection();
                Statement statement = c.createStatement();
        ) {
            String query = "SELECT * FROM users ";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String email = res.getString(3);
                System.out.println("ID: " + id + "\nName: " + name + "\nEmail: " + email + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //UPDATE
    public void updateData(int id) {
        try (
                Connection c = MySQLConnection.getConnection();
                PreparedStatement statement = c.prepareStatement(
                        "UPDATE users SET username=? WHERE id=?"
                );
        ) {
            String newUsername = "Cute";
            statement.setString(1, newUsername);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);
            ResultSet res = statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE
    public void deleteData(int id) {
        try (
                Connection c = MySQLConnection.getConnection();
                PreparedStatement statement = c.prepareStatement(
                        "DELETE FROM users WHERE id=?"
                );
        ) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows Deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
