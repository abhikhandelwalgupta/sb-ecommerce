package com.ecommerce.repositories;

import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByusername(String username);
    Boolean existsByusername(String username);

    Boolean existsByEmail(String email);
}
