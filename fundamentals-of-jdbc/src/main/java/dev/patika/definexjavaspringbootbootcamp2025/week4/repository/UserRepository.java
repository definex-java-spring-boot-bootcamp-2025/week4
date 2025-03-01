package dev.patika.definexjavaspringbootbootcamp2025.week4.repository;

import dev.patika.definexjavaspringbootbootcamp2025.week4.config.DatabaseConnection;
import dev.patika.definexjavaspringbootbootcamp2025.week4.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {

    public UserRepository() {
        this.initUsersTable();
    }

    private void initUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users (\n" +
                "    id uuid,\n" +
                "    first_name VARCHAR NOT NULL,\n" +
                "    last_name VARCHAR NOT NULL,\n" +
                "    email VARCHAR NOT NULL,\n" +
                "    PRIMARY KEY (id)\n" +
                ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            boolean result = stmt.execute(SQL);
            if (result) {
                System.out.println("Table is initialized.");
                return;
            }
            System.out.println("Table cannot be initialized.");
        } catch (SQLException e) {
            System.out.println("Table initialization failed.");
            e.printStackTrace();
        }
    }

    public List<User> readUsers() {
        String SQL = "select id, first_name, last_name, email from users order by first_name";
        List<User> users = new ArrayList<>();

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                ) {
            while (rs.next()) {
                User user = User.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .build();
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }

    public boolean create(User user) {
        String SQL = "INSERT INTO users (id, first_name, last_name, email) VALUES (?::uuid, ?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)
        ) {
            pstmt.setString(1, user.getId().toString());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        String SQL = "update users set first_name = ?, last_name = ?, email = ? where id = ?::uuid";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getId().toString());
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean delete(UUID id) {
        String SQL = "delete from users where id = ?::uuid";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(4, id.toString());
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean dropTable() {
        String sql = "DROP TABLE users;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
