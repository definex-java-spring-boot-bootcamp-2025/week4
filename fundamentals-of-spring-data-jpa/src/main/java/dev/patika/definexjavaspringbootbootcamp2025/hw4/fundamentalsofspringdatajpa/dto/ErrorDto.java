package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private String message;
    private String details;
}
