package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.CustomerRegistrationDao;
import com.TraineProject.CustomerService.dto.CustomerDto;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import com.TraineProject.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRegistrationDao customerRegistrationDao ;


    @Autowired
    public CustomerServiceImpl (CustomerRegistrationDao customerRegistrationDao)
    {
        this.customerRegistrationDao = customerRegistrationDao ;
    }

    public CustomerEntity changeCustomerDtoToCustomerEntity ( CustomerDto customerDto)
    {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPassword(customerDto.getPassword());

        return customerEntity ;
    }

    public CustomerResponseDto changeCustomerEntityToCustomerResponseDto ( CustomerEntity customerEntity )
    {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(customerEntity.getName());
        customerResponseDto.setEmail(customerEntity.getEmail());

        return customerResponseDto ;
    }

    @Override
    public CustomerResponseDto customerRegistration (CustomerDto customerDto )
    {
        CustomerEntity customerEntity = changeCustomerDtoToCustomerEntity(customerDto);
        return changeCustomerEntityToCustomerResponseDto(customerRegistrationDao.customerRegister(customerEntity));

       // return "user registered successfully";
    }

    @Override
    public CustomerResponseDto customerLogin(CustomerLoginDto customerLoginDto )
    {
        Optional<CustomerEntity> optionalCustomer = customerRegistrationDao.findByEmail(customerLoginDto.getEmail());
    }
}
