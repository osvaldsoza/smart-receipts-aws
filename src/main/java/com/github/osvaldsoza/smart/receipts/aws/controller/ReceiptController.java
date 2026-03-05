package com.github.osvaldsoza.smart.receipts.aws.controller;

import com.github.osvaldsoza.smart.receipts.aws.dto.CreateReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ResponseReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * REST Controller for Receipt management with file upload support
 */
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

   /**
     * Create a new receipt with file upload
     * @param createReceiptDTO the receipt data
     * @param file the file to upload
     * @return the created receipt
     * @throws IOException if file processing fails
     */
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseReceiptDTO> createReceiptWithFile(
            @RequestPart("metadata") String metadata,
            @RequestPart("file") MultipartFile file) throws IOException{
        CreateReceiptDTO createReceiptDTO = new ObjectMapper().readValue(metadata, CreateReceiptDTO.class);
        ResponseReceiptDTO created = receiptService.createReceiptWithFile(createReceiptDTO, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get receipt by ID
     * @param id the receipt ID
     * @return the receipt
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseReceiptDTO> getReceipt(@PathVariable UUID id) {
        ResponseReceiptDTO receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    /**
     * Get all receipts
     * @return list of all receipts
     */
    @GetMapping
    public ResponseEntity<List<ResponseReceiptDTO>> getAllReceipts() {
        List<ResponseReceiptDTO> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    /**
     * Get all receipts by user ID
     * @param userId the user ID
     * @return list of user's receipts
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResponseReceiptDTO>> getReceiptsByUser(@PathVariable String userId) {
        List<ResponseReceiptDTO> receipts = receiptService.getReceiptsByUserId(userId);
        return ResponseEntity.ok(receipts);
    }

    /**
     * Update receipt
     * @param id the receipt ID
     * @param receiptDTO the updated receipt data
     * @return the updated receipt
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReceiptDTO> updateReceipt(
            @PathVariable UUID id,
            @RequestBody ReceiptDTO receiptDTO) {
        ReceiptDTO updated = receiptService.updateReceipt(id, receiptDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete receipt
     * @param id the receipt ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable UUID id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
