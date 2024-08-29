package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.CustomerRegistrationDao;
import com.TraineProject.CustomerService.dto.CustomerLoginDto;
import com.TraineProject.CustomerService.dto.CustomerResponseDto;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import com.TraineProject.CustomerService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    CustomerResponseDto customerLogin (CustomerLoginDto customerLoginDto )
    {

    }
}
