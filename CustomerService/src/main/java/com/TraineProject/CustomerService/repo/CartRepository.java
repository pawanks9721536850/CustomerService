package com.TraineProject.CustomerService.repo;

import com.TraineProject.CustomerService.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface CartRepository extends JpaRepository<CartEntity , UUID> {
    Optional<CartEntity> findCartByCustomerId (UUID customerId );
}
