package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateReceiptResponse {
    private String id;
    private String status;
}
