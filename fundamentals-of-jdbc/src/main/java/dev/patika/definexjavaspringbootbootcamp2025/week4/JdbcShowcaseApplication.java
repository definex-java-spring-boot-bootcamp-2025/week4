package dev.patika.definexjavaspringbootbootcamp2025.week4;

import dev.patika.definexjavaspringbootbootcamp2025.week4.entity.User;
import dev.patika.definexjavaspringbootbootcamp2025.week4.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class JdbcShowcaseApplication {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        System.out.println("----- USERS -----");
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .firstName("Gökhan")
                .lastName("Tamkoç")
                .email("user1@test.com")
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .firstName("Muaz")
                .lastName("Memiş")
                .email("user2@test.com")
                .build();

        boolean user1CreateRes = userRepository.create(user1);
        if (user1CreateRes) {
            System.out.println("user 1 created!");
        }

        boolean user2CreateRes = userRepository.create(user2);
        if (user2CreateRes) {
            System.out.println("user 2 created!");
        }

        System.out.println("----- USERS * LIST -----");
        List<User> users = userRepository.readUsers();
        for (User user : users) {
            System.out.println(user);
        }

        User userToUpdate = users.getFirst();

        System.out.println("----- USERS * UPDATE -----");

        userToUpdate.setFirstName("Muaz");
        userToUpdate.setLastName("Memiş");

        boolean user1UpdateResult = userRepository.update(userToUpdate);
        if (user1UpdateResult) {
            System.out.println("user 1 updated!");
        }

        System.out.println("----- USERS * DELETE -----");

        boolean user1DeleteResult = userRepository.delete(users.getFirst().getId());
        if (user1DeleteResult) {
            System.out.println("user 1 deleted!");
        }

        System.out.println("----- USERS * LIST -----");
        users = userRepository.readUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userRepository.dropTable();
    }
}
