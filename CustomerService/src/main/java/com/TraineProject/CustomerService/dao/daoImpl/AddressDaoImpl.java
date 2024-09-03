package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.AddressDao;
import com.TraineProject.CustomerService.entity.AddressEntity;
import com.TraineProject.CustomerService.repo.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressDaoImpl implements AddressDao {

    private final AddressRepository addressRepository ;

    public AddressDaoImpl ( AddressRepository addressRepository )
    {
        this.addressRepository = addressRepository ;
    }
    public void saveAddress ( AddressEntity addressEntity )
    {
        addressRepository.save(addressEntity);
    }
}
