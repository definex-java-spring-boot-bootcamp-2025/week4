package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cart {
    private String ownerName;
    private String id;
}
