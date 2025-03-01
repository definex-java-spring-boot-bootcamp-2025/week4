package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.controller;

import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto.UserDto;
import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/v1")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(UUID.fromString(id)));
    }

    @PutMapping("/v1")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                userService.updateUser(userDto)
        );
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
