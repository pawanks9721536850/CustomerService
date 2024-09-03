package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.CustomerRegistrationDao;
import com.TraineProject.CustomerService.entity.CustomerEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final CustomerRegistrationDao customerRegistrationDao ;

    @Autowired
    public TokenService ( CustomerRegistrationDao customerRegistrationDao)
    {
        this.customerRegistrationDao= customerRegistrationDao;
    }

    private static final int tokenLength = 10;

    public String generateToken() {
        return RandomStringUtils.randomAlphanumeric(tokenLength);
    }

    public boolean isValidToken( String token , UUID customerId  ) throws Exception
    {
        Optional<CustomerEntity> optionalCustomer =  customerRegistrationDao.findByToken(token);
        //System.out.println("customerEntity:   "+customerEntity.isEmpty() );
        CustomerEntity customerEntity ;
        if ( optionalCustomer.isPresent() )
        {
            customerEntity = optionalCustomer.get() ;
            if (customerEntity.getId().equals(customerId)) {
                return true;
            }
            else {
                throw new RuntimeException ("customerId does not exists");
            }
        }

         else
        {
            throw new RuntimeException("user not found ");
        }
    }
}
