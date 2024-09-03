package com.TraineProject.CustomerService.repo;

import com.TraineProject.CustomerService.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
