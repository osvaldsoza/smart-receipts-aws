package com.github.osvaldsoza.smart.receipts.aws.service.mapper;

import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for Receipt entity to ReceiptDTO and vice versa using MapStruct
 * This mapper is automatically implemented by MapStruct at compile-time
 */
@Mapper(componentModel = "spring")
public interface ReceiptMapper {

    /**
     * Convert Receipt entity to ReceiptDTO
     * @param receipt the Receipt entity
     * @return the ReceiptDTO
     */
    @Mapping(source = "user.id", target = "userId")
    ReceiptDTO toDTO(Receipt receipt);

    /**
     * Convert ReceiptDTO to Receipt entity
     * @param dto the ReceiptDTO
     * @return the Receipt entity
     */
    @Mapping(source = "userId", target = "user.id")
    Receipt toEntity(ReceiptDTO dto);

    /**
     * Update an existing Receipt entity from ReceiptDTO
     * @param dto the ReceiptDTO
     * @param receipt the Receipt entity to update
     */
    @Mapping(source = "userId", target = "user.id")
    void updateEntityFromDTO(ReceiptDTO dto, @MappingTarget Receipt receipt);
}

