package com.TraineProject.CustomerService.dao;

import com.TraineProject.CustomerService.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface PaymentDao {
    PaymentEntity savePayment(PaymentEntity paymentEntity);
    Optional<PaymentEntity> findByCustomerId (UUID customerId );
}
