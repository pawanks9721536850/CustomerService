package com.TraineProject.CustomerService.service;

import com.TraineProject.CustomerService.dto.CustomerDto;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.exceptionHandler.InvalidUserException;
import org.springframework.stereotype.Service;


public interface CustomerService {

        CustomerResponseDto customerRegistration (CustomerDto customerDto );
        CustomerResponseDto  customerLogin(CustomerLoginDto customerLoginDto) throws InvalidUserException;


}
