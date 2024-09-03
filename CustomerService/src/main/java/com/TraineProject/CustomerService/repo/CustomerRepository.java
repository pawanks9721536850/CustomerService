package com.TraineProject.CustomerService.repo;

import com.TraineProject.CustomerService.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity , UUID> {
   Optional<CustomerEntity> findByEmail (String email );
   Optional<CustomerEntity> findByToken( String token );
}
