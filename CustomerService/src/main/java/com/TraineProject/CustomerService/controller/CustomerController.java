package com.TraineProject.CustomerService.controller;

import com.TraineProject.CustomerService.dto.CustomerDto;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService ;

    @Autowired
    public CustomerController ( CustomerService customerService ) throws Exception
    {
        this.customerService = customerService ;
    }

    @PostMapping(path="/register")
    public CustomerResponseDto customerRegistration (@Valid @RequestBody CustomerDto customerDto )
    {
        return customerService.customerRegistration(customerDto);

    }

    @PostMapping(path="/login")
    public CustomerResponseDto customerLogin ( @RequestBody CustomerLoginDto customerLoginDto )
    {
        return customerService.customerLogin( customerLoginDto );
    }

}
