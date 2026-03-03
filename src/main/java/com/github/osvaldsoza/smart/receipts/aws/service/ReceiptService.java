package com.github.osvaldsoza.smart.receipts.aws.service;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import com.github.osvaldsoza.smart.receipts.aws.repository.ReceiptRepository;
import com.github.osvaldsoza.smart.receipts.aws.service.mapper.ReceiptMapper;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    private final ReceiptMapper receiptMapper;

    public ReceiptService(ReceiptRepository receiptRepository, ReceiptMapper receiptMapper) {
        this.receiptRepository = receiptRepository;
        this.receiptMapper = receiptMapper;
    }

    public Receipt createReceipt(ReceiptDTO receiptDTO) {
        var receipt = receiptMapper.toEntity(receiptDTO);
        return receiptRepository.save(receipt);
    }
}
