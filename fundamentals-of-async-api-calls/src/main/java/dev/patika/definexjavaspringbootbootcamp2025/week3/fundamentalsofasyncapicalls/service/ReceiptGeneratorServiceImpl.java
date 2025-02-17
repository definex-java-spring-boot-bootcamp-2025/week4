package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.service;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.exception.NoSuchReceiptGenerationProcessException;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.exception.ReceiptGenerationException;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.GenerateReceiptRequest;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofasyncapicalls.model.ReceiptStatusResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ReceiptGeneratorServiceImpl implements ReceiptGeneratorService {

    private ExecutorService executorService;

    private Map<String, String> receiptGenerationJobs;

    public ReceiptGeneratorServiceImpl() {
        this.executorService = Executors.newVirtualThreadPerTaskExecutor();
        this.receiptGenerationJobs = new ConcurrentHashMap<>();
    }

    @Override
    public CompletableFuture<String> generateReceipt(GenerateReceiptRequest generateReceiptRequest) throws ReceiptGenerationException {
        String jobId = generateReceiptRequest.getId();
        receiptGenerationJobs.put(jobId, "PROCESSING");
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate Document Generation
                Thread.sleep(10000);
                receiptGenerationJobs.put(jobId, "COMPLETED");
                return jobId;
            } catch (InterruptedException iex) {
                Thread.currentThread().interrupt();
                receiptGenerationJobs.put(jobId, "FAILED");
                throw new RuntimeException("Document generation failed");
            }
        }, executorService);
    }

    @Override
    public ReceiptStatusResponse getReceiptGenerationStatus(String id) throws NoSuchReceiptGenerationProcessException {
        String status = this.receiptGenerationJobs.get(id);
        return ReceiptStatusResponse.builder().id(id).status(status).build();
    }
}
