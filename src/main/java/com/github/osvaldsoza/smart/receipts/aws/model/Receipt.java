package com.github.osvaldsoza.smart.receipts.aws.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.github.osvaldsoza.smart.receipts.aws.model.enums.ReceiptStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Receipt Model representing the core business entity
 */
@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReceiptStatus status;

    private String description;

    private BigDecimal amount;

    private String originalKey;

    private String processedKey;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] fileData;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileContentType;

    @Column(nullable = false)
    private Long fileSize;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Receipt() {
    }

    public Receipt(User user, ReceiptStatus status, String description, BigDecimal amount, String originalKey, String processedKey, byte[] fileData, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.status = status;
        this.description = description;
        this.amount = amount;
        this.originalKey = originalKey;
        this.processedKey = processedKey;
        this.fileData = fileData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReceiptStatus getStatus() {
        return status;
    }

    public void setStatus(ReceiptStatus status) {
        this.status = status;
    }

    public String getOriginalKey() {
        return originalKey;
    }

    public void setOriginalKey(String originalKey) {
        this.originalKey = originalKey;
    }

    public String getProcessedKey() {
        return processedKey;
    }

    public void setProcessedKey(String processedKey) {
        this.processedKey = processedKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

        public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}

