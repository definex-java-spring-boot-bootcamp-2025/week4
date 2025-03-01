package dev.patika.definexjavaspringbootbootcamp2025.week4.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/patikadev";
    private static final String USER = "patika.dev";
    private static final String PASSWORD = "patika.dev";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
