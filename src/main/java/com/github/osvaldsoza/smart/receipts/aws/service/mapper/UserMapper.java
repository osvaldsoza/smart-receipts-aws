package com.github.osvaldsoza.smart.receipts.aws.service.mapper;

import com.github.osvaldsoza.smart.receipts.aws.dto.UserDTO;
import com.github.osvaldsoza.smart.receipts.aws.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper for User entity to UserDTO and vice versa using MapStruct
 * This mapper is automatically implemented by MapStruct at compile-time
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convert User entity to UserDTO
     * @param user the User entity
     * @return the UserDTO
     */
    UserDTO toDTO(User user);

    /**
     * Convert UserDTO to User entity
     * @param dto the UserDTO
     * @return the User entity
     */
    User toEntity(UserDTO dto);

    /**
     * Update an existing User entity from UserDTO
     * @param dto the UserDTO
     * @param user the User entity to update
     */
    void updateEntityFromDTO(UserDTO dto, @MappingTarget User user);
}

