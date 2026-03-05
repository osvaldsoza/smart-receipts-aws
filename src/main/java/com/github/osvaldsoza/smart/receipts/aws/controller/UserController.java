package com.github.osvaldsoza.smart.receipts.aws.controller;

import com.github.osvaldsoza.smart.receipts.aws.dto.UserDTO;
import com.github.osvaldsoza.smart.receipts.aws.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

   private UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping
        public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
            UserDTO userCreated = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        }
}
