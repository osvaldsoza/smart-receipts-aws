package com.github.osvaldsoza.smart.receipts.aws.dto;

import java.math.BigDecimal;

public record CreateReceiptDTO(String userId, String description, BigDecimal amount) {
}
