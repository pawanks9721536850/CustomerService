package com.TraineProject.CustomerService.service;

import com.TraineProject.CustomerService.dto.AddressDto;
import com.TraineProject.CustomerService.dto.CartDto;
import com.TraineProject.CustomerService.dto.CheckoutResponseDto;
import com.TraineProject.CustomerService.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CartService {
     CartDto addToCart (ProductDto productDto , UUID customerId );
     CheckoutResponseDto checkOut(UUID customerId , AddressDto addressDto);
}
