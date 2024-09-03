package com.TraineProject.CustomerService.dao;

import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRegistrationDao  {
    CustomerEntity customerRegister(CustomerEntity customerEntity);

    CustomerEntity customerLogin(CustomerLoginDto customerLoginDto);

     Optional<CustomerEntity> findByEmail(String email);
     Optional<CustomerEntity> findByToken( String token );
}
