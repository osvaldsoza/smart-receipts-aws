package com.github.osvaldsoza.smart.receipts.aws.dto;

import java.util.UUID;

/**
 * DTO for Receipt data transfer
 */
public record ReceiptDTO(
        UUID id,
        String userId,
        String status,
        String originalKey,
        String processedKey
) {
}



