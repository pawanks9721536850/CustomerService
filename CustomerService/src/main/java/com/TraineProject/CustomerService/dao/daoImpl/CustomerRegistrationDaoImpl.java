package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.CustomerRegistrationDao;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import com.TraineProject.CustomerService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {

    private CustomerRepository customerRepository ;

    @Autowired
    public CustomerRegistrationDaoImpl (CustomerRepository customerRepository )
    {
        this.customerRepository= customerRepository ;
    }

    @Override
    public CustomerEntity customerRegister (CustomerEntity customerEntity )
    {
         return customerRepository.save( customerEntity );
    }

    @Override
    public CustomerEntity customerLogin (CustomerLoginDto customerLoginDto )
    {
        Optional<CustomerEntity> customer = customerRepository.findByEmail(customerLoginDto.getEmail());

        if ( customer.isPresent() )
        {
            CustomerEntity presentCustomer = customer.get() ;
            if ( presentCustomer.getPassword().equals ( customerLoginDto.getPassword() ))
            {
                return presentCustomer ;
            }
            else
            {
                throw new RuntimeException("password does not match");
            }

        }
        else
        {
            throw new RuntimeException("Customer does not exists, register first" );
        }
    }
}
