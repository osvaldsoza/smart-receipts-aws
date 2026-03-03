package com.github.osvaldsoza.smart.receipts.aws.repository;

import com.github.osvaldsoza.smart.receipts.aws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Custom query methods can be added here
    Optional<User> findByUsername(String username);
}

