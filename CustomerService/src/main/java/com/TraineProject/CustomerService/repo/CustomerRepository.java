package com.TraineProject.CustomerService.repo;

import com.TraineProject.CustomerService.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity , UUID> {

}
