package com.TraineProject.CustomerService.service.serviceImpl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenService {


        private static final int tokenLength = 10 ;
    public String generateToken()
    {
        return RandomStringUtils.randomAlphanumeric(tokenLength);
    }
}
