package com.TraineProject.CustomerService.service;

import com.TraineProject.CustomerService.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    String paymentProcess ( PaymentDto paymentDto );
}
