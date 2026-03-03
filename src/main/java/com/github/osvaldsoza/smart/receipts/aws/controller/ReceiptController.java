package com.github.osvaldsoza.smart.receipts.aws.controller;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.service.ReceiptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public void createReceipt(@RequestBody ReceiptDTO receiptDTO) {
        receiptService.createReceipt(receiptDTO);
    }
}
