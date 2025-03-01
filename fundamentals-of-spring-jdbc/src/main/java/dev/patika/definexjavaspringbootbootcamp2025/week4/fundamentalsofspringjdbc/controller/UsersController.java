package dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.controller;

import dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.entity.User;
import dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 CREATE TABLE IF NOT EXISTS users (
 id uuid,
 first_name VARCHAR NOT NULL,
 last_name VARCHAR NOT NULL,
 email VARCHAR NOT NULL,
 PRIMARY KEY (id)
 );
 */

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/v1")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return userRepository.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/v1")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/v1")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.update(user));
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
