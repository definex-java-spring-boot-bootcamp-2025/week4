package dev.patika.definexjavaspringbootbootcamp2025.week4.fundamentalsofspringjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
