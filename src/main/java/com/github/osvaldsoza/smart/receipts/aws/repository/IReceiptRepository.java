package com.github.osvaldsoza.smart.receipts.aws.repository;

import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import java.util.List;

/**
 * Repository interface for Receipt data access
 */
public interface IReceiptRepository {
    Receipt findById(Long id);
    List<Receipt> findAll();
    Receipt save(Receipt receipt);
    void update(Receipt receipt);
    void delete(Long id);
}

