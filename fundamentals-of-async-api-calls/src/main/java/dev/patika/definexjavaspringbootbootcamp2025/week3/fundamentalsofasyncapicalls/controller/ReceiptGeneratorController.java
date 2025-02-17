package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.controller;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.GenerateReceiptRequest;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.GenerateReceiptResponse;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.service.ReceiptGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptGeneratorController {

    private ReceiptGeneratorService receiptGeneratorService;

    public ReceiptGeneratorController(ReceiptGeneratorService receiptGeneratorService) {
        this.receiptGeneratorService = receiptGeneratorService;
    }

    @PostMapping("/v1/generateReceipt")
    public ResponseEntity<GenerateReceiptResponse> generateReceipt(@RequestBody GenerateReceiptRequest generateReceiptRequest) {
        this.receiptGeneratorService.generateReceipt(generateReceiptRequest);
        return ResponseEntity.ok(
                GenerateReceiptResponse.builder()
                        .id(generateReceiptRequest.getId())
                        .status("PROCESSING")
                        .build()
        );
    }

    @GetMapping("/v1/status/{id}")
    public ResponseEntity<?> receiptStatus(@PathVariable String id) {
        return ResponseEntity.ok(
                receiptGeneratorService.getReceiptGenerationStatus(id)
        );
    }
}
