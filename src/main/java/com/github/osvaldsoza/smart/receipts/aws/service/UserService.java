package com.github.osvaldsoza.smart.receipts.aws.service;

import com.github.osvaldsoza.smart.receipts.aws.dto.UserDTO;
import com.github.osvaldsoza.smart.receipts.aws.repository.UserRepository;
import com.github.osvaldsoza.smart.receipts.aws.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private  final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void createUser(UserDTO userDTO) {
        var user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }

}
