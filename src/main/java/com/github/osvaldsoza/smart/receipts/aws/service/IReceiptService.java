package com.github.osvaldsoza.smart.receipts.aws.service;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import java.util.List;

/**
 * Service interface for Receipt business logic
 */
public interface IReceiptService {
    ReceiptDTO getReceiptById(Long id);
    List<ReceiptDTO> getAllReceipts();
    ReceiptDTO createReceipt(ReceiptDTO receiptDTO);
    ReceiptDTO updateReceipt(Long id, ReceiptDTO receiptDTO);
    void deleteReceipt(Long id);
}

