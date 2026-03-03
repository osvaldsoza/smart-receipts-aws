package com.github.osvaldsoza.smart.receipts.aws.model;

import java.time.LocalDateTime;

/**
 * Receipt Model representing the core business entity
 */
public class Receipt {
    private Long id;
    private String merchantName;
    private Double amount;
    private LocalDateTime transactionDate;
    private String category;
    private String description;

    public Receipt() {
    }

    public Receipt(Long id, String merchantName, Double amount, LocalDateTime transactionDate, String category, String description) {
        this.id = id;
        this.merchantName = merchantName;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.category = category;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

