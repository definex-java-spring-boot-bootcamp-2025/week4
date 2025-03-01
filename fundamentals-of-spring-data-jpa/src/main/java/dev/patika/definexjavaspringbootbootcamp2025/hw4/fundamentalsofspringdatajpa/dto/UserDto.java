package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
