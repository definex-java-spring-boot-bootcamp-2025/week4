package dev.patika.definexjavaspringbootbootcamp2025.week4.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
