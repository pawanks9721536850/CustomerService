package com.TraineProject.CustomerService.dao;

import com.TraineProject.CustomerService.entity.AddressEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressDao {
    void saveAddress ( AddressEntity addressEntity);
}
