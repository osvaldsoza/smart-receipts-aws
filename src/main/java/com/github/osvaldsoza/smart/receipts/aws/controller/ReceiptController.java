package com.github.osvaldsoza.smart.receipts.aws.controller;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Receipt operations
 */
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private IReceiptService receiptService;

    /**
     * Get all receipts
     * @return List of all receipts
     */
    @GetMapping
    public ResponseEntity<List<ReceiptDTO>> getAllReceipts() {
        List<ReceiptDTO> receipts = receiptService.getAllReceipts();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    /**
     * Get receipt by ID
     * @param id Receipt ID
     * @return Receipt details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable Long id) {
        ReceiptDTO receipt = receiptService.getReceiptById(id);
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create new receipt
     * @param receiptDTO Receipt data
     * @return Created receipt
     */
    @PostMapping
    public ResponseEntity<ReceiptDTO> createReceipt(@RequestBody ReceiptDTO receiptDTO) {
        ReceiptDTO createdReceipt = receiptService.createReceipt(receiptDTO);
        return new ResponseEntity<>(createdReceipt, HttpStatus.CREATED);
    }

    /**
     * Update existing receipt
     * @param id Receipt ID
     * @param receiptDTO Updated receipt data
     * @return Updated receipt
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReceiptDTO> updateReceipt(@PathVariable Long id, @RequestBody ReceiptDTO receiptDTO) {
        ReceiptDTO updatedReceipt = receiptService.updateReceipt(id, receiptDTO);
        if (updatedReceipt != null) {
            return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete receipt
     * @param id Receipt ID
     * @return Success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

