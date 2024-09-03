package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.CustomerRegistrationDao;
import com.TraineProject.CustomerService.dto.CustomerDto;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import com.TraineProject.CustomerService.exceptionHandler.InvalidUserException;
import com.TraineProject.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRegistrationDao customerRegistrationDao;
    private final TokenService tokenService;


    @Autowired
    public CustomerServiceImpl(CustomerRegistrationDao customerRegistrationDao, TokenService tokenService) {
        this.customerRegistrationDao = customerRegistrationDao;
        this.tokenService = tokenService;
    }

    public CustomerEntity changeCustomerDtoToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPassword(customerDto.getPassword());

        return customerEntity;
    }

    public CustomerResponseDto changeCustomerEntityToCustomerResponseDto(CustomerEntity customerEntity) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(customerEntity.getName());
        customerResponseDto.setEmail(customerEntity.getEmail());

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto customerRegistration(CustomerDto customerDto) {
        CustomerEntity customerEntity = changeCustomerDtoToCustomerEntity(customerDto);
        return changeCustomerEntityToCustomerResponseDto(customerRegistrationDao.customerRegister(customerEntity));


    }

    @Override
    public CustomerResponseDto customerLogin(CustomerLoginDto customerLoginDto) throws InvalidUserException {
        Optional<CustomerEntity> Customer = customerRegistrationDao.findByEmail(customerLoginDto.getEmail());
        CustomerEntity customerEntity;
        if (Customer.isPresent()) {
            customerEntity = Customer.get();
        } else {
            throw new InvalidUserException("Invalid email or password ");
        }

        if (!customerEntity.getPassword().equals(customerLoginDto.getPassword())) {
            throw new InvalidUserException("password does not match");
        }

        if (customerEntity.getToken() == null || LocalDateTime.now().isAfter(customerEntity.getTokenExpiration())) {
            System.out.println("time:  " + LocalDateTime.now());
            String token = tokenService.generateToken();
            System.out.println("token :" + token);
            LocalDateTime tokenExpiration = LocalDateTime.now().plusHours(1);
            customerEntity.setToken(token);
            customerEntity.setTokenExpiration(tokenExpiration);
            customerRegistrationDao.customerLogin(customerLoginDto);
            customerRegistrationDao.customerRegister(customerEntity);
        }

        return changeCustomerEntityToCustomerResponseDto(customerEntity);

    }
}
