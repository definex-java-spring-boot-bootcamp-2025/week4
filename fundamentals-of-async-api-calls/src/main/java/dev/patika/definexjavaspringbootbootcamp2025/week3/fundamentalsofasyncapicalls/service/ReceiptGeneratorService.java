package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.service;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.exception.NoSuchReceiptGenerationProcessException;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.exception.ReceiptGenerationException;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.GenerateReceiptRequest;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.ReceiptStatusResponse;

import java.util.concurrent.CompletableFuture;

public interface ReceiptGeneratorService {
    CompletableFuture<String> generateReceipt(
            GenerateReceiptRequest generateReceiptRequest) throws ReceiptGenerationException;

    ReceiptStatusResponse getReceiptGenerationStatus(String id) throws NoSuchReceiptGenerationProcessException;
}
