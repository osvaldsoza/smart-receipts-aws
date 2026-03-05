package com.github.osvaldsoza.smart.receipts.aws.service;

import com.github.osvaldsoza.smart.receipts.aws.dto.CreateReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ResponseReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.exception.ResourceNotFoundException;
import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import com.github.osvaldsoza.smart.receipts.aws.model.enums.ReceiptStatus;
import com.github.osvaldsoza.smart.receipts.aws.repository.ReceiptRepository;
import com.github.osvaldsoza.smart.receipts.aws.service.mapper.ReceiptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    public ReceiptService(ReceiptRepository receiptRepository, ReceiptMapper receiptMapper) {
        this.receiptRepository = receiptRepository;
        this.receiptMapper = receiptMapper;
    }

    /**
     * Create a new receipt with file upload
     *
     * @param receiptDTO the receipt data
     * @param file       the file to upload
     * @return the created receipt DTO
     * @throws IOException if file reading fails
     */
    public ResponseReceiptDTO createReceiptWithFile(CreateReceiptDTO createReceiptDTO, MultipartFile file) throws IOException {
        Receipt receipt = receiptMapper.toEntity(createReceiptDTO);
        receipt.setStatus(ReceiptStatus.PENDING);
        receipt = initializeFileData(file, receipt);

        Receipt saved = receiptRepository.save(receipt);

        return receiptMapper.toResponseDTO(saved);
    }

    private Receipt initializeFileData(MultipartFile file, Receipt receipt) throws IOException {
        if (file != null && !file.isEmpty()) {
            receipt.setFileData(file.getBytes());
            receipt.setFileName(file.getOriginalFilename());
            receipt.setFileContentType(file.getContentType());
            receipt.setFileSize(file.getSize());
        }
        return receipt;
    }

    /**
     * Get receipt by ID
     *
     * @param id the receipt ID
     * @return the receipt DTO
     */
    @Transactional(readOnly = true)
    public ResponseReceiptDTO getReceiptById(UUID id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));
        return receiptMapper.toResponseDTO(receipt);
    }

    /**
     * Get all receipts
     *
     * @return list of receipt DTOs
     */
    @Transactional(readOnly = true)
    public List<ResponseReceiptDTO> getAllReceipts() {
        return receiptRepository.findAll()
                .stream()
                .map(receiptMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all receipts by user ID
     *
     * @param userId the user ID
     * @return list of receipt DTOs
     */
    @Transactional(readOnly = true)
    public List<ResponseReceiptDTO> getReceiptsByUserId(String userId) {
        return receiptRepository.findByUserId(userId)
                .stream()
                .map(receiptMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update receipt
     *
     * @param id         the receipt ID
     * @param receiptDTO the receipt data
     * @return the updated receipt DTO
     */
    public ReceiptDTO updateReceipt(UUID id, ReceiptDTO receiptDTO) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));

        receiptMapper.updateEntityFromDTO(receiptDTO, receipt);
        Receipt updated = receiptRepository.save(receipt);
        return receiptMapper.toDTO(updated);
    }

    /**
     * Update receipt with file
     *
     * @param id         the receipt ID
     * @param receiptDTO the receipt data
     * @param file       the file to upload
     * @return the updated receipt DTO
     * @throws IOException if file reading fails
     */
    public ReceiptDTO updateReceiptWithFile(UUID id, ReceiptDTO receiptDTO, MultipartFile file) throws IOException {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));

        receiptMapper.updateEntityFromDTO(receiptDTO, receipt);

        receipt = initializeFileData(file, receipt);

        Receipt updated = receiptRepository.save(receipt);
        return receiptMapper.toDTO(updated);
    }

    /**
     * Delete receipt
     *
     * @param id the receipt ID
     */
    public void deleteReceipt(UUID id) {
        if (!receiptRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
    }
}
