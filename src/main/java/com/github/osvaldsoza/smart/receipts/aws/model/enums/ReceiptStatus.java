package com.github.osvaldsoza.smart.receipts.aws.model.enums;

/**
 * Enum representing the status of a Receipt
 */
public enum ReceiptStatus {
    PENDING("Pendente"),
    UPDATING("Pendente"),
    PROCESSING("Processando"),
    PROCESSED("Processado"),
    FAILED("Falha"),
    ARCHIVED("Arquivado");

    private final String displayName;

    ReceiptStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

