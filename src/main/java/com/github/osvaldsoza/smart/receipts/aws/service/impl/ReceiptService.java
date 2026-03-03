package com.github.osvaldsoza.smart.receipts.aws.service.impl;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import com.github.osvaldsoza.smart.receipts.aws.repository.IReceiptRepository;
import com.github.osvaldsoza.smart.receipts.aws.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Receipt business logic
 */
@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private IReceiptRepository receiptRepository;

    @Override
    public ReceiptDTO getReceiptById(Long id) {
        Receipt receipt = receiptRepository.findById(id);
        return convertToDTO(receipt);
    }

    @Override
    public List<ReceiptDTO> getAllReceipts() {
        return receiptRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDTO createReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = convertToEntity(receiptDTO);
        Receipt savedReceipt = receiptRepository.save(receipt);
        return convertToDTO(savedReceipt);
    }

    @Override
    public ReceiptDTO updateReceipt(Long id, ReceiptDTO receiptDTO) {
        Receipt receipt = receiptRepository.findById(id);
        if (receipt != null) {
            receipt.setMerchantName(receiptDTO.getMerchantName());
            receipt.setAmount(receiptDTO.getAmount());
            receipt.setTransactionDate(receiptDTO.getTransactionDate());
            receipt.setCategory(receiptDTO.getCategory());
            receipt.setDescription(receiptDTO.getDescription());
            receiptRepository.update(receipt);
        }
        return convertToDTO(receipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.delete(id);
    }

    private ReceiptDTO convertToDTO(Receipt receipt) {
        if (receipt == null) {
            return null;
        }
        return new ReceiptDTO(
                receipt.getId(),
                receipt.getMerchantName(),
                receipt.getAmount(),
                receipt.getTransactionDate(),
                receipt.getCategory(),
                receipt.getDescription()
        );
    }

    private Receipt convertToEntity(ReceiptDTO receiptDTO) {
        if (receiptDTO == null) {
            return null;
        }
        return new Receipt(
                receiptDTO.getId(),
                receiptDTO.getMerchantName(),
                receiptDTO.getAmount(),
                receiptDTO.getTransactionDate(),
                receiptDTO.getCategory(),
                receiptDTO.getDescription()
        );
    }
}

