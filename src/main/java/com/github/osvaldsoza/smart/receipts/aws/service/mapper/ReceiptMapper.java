package com.github.osvaldsoza.smart.receipts.aws.service.mapper;

import com.github.osvaldsoza.smart.receipts.aws.dto.CreateReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.dto.ResponseReceiptDTO;
import com.github.osvaldsoza.smart.receipts.aws.model.Receipt;
import com.github.osvaldsoza.smart.receipts.aws.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

/**
 * Mapper for Receipt entity to ReceiptDTO and vice versa using MapStruct
 * This mapper is automatically implemented by MapStruct at compile-time
 */
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReceiptMapper {

    /**
     * Convert Receipt entity to ReceiptDTO
     * @param receipt the Receipt entity
     * @return the ReceiptDTO
     */
    ResponseReceiptDTO toResponseDTO(Receipt receipt);

    /**
     * Convert Receipt entity to ReceiptDTO
     * @param receipt the Receipt entity
     * @return the ReceiptDTO
     */
    ReceiptDTO toDTO(Receipt receipt);


    /**
     * Convert ReceiptDTO to Receipt entity
     * Use a helper method to map userId -> User
     * Ignores fileData as it is handled separately
     * @param dto the ReceiptDTO
     * @return the Receipt entity
     */
    Receipt toEntity(ReceiptDTO dto);


    /**
     * Convert ReceiptDTO to Receipt entity
     * Use a helper method to map userId -> User
     * Ignores fileData as it is handled separately
     * @param dto the ReceiptDTO
     * @return the Receipt entity
     */
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "fileData", ignore = true)
    Receipt toEntity(CreateReceiptDTO dto);


    /**
     * Update an existing Receipt entity from ReceiptDTO
     * Will update fields but handle user mapping carefully
     * @param dto the ReceiptDTO
     * @param receipt the Receipt entity to update
     */
    void updateEntityFromDTO(ReceiptDTO dto, @MappingTarget Receipt receipt);

    // Helper mapping: Map a simple userId String to a User entity (MapStruct will use this)
    default User mapUserIdToUser(String userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(UUID.fromString(userId));
        return user;
    }
}
