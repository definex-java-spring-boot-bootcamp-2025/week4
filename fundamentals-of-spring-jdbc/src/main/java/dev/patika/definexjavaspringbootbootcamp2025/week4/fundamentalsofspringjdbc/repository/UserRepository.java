package dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.repository;

import dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.entity.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Transactional
    public User save(User user) {
        try {
            String sql = "INSERT INTO users(id, first_name, last_name, email) VALUES(:id::uuid, :firstName, :lastName, :email)";
            user.setId(UUID.randomUUID());

            jdbcClient.sql(sql)
                    .param("id", user.getId().toString())
                    .param("firstName", user.getFirstName())
                    .param("lastName", user.getLastName())
                    .param("email", user.getEmail())
                    .update();
            return user;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Optional<User> findById(UUID id) {
        String sql = "SELECT * FROM users WHERE id = :id::uuid";
        return jdbcClient.sql(sql)
                .param("id", id.toString())
                .query(User.class)
                .optional();
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY first_name";
        return jdbcClient.sql(sql)
                .query(User.class)
                .list();
    }

    @Transactional
    public User update(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?::uuid";
        int updated = jdbcClient.sql(sql)
                .param(1, user.getFirstName())
                .param(2, user.getLastName())
                .param(3, user.getEmail())
                .param(4, user.getId().toString())
                .update();

        if (updated == 0) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Transactional
    public void deleteById(UUID id) {
        String sql = "DELETE FROM users WHERE id = ?::uuid";
        int deleted = jdbcClient.sql(sql)
                .param(1, id.toString())
                .update();

        if (deleted == 0) {
            throw new RuntimeException("User not found");
        }
    }
}