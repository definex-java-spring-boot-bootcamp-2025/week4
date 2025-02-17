package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model;

import lombok.Data;

@Data
public class GenerateReceiptRequest {
    private String id;
    private String receiptData;
}
