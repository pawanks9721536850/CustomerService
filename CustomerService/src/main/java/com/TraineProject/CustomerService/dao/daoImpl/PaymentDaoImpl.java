package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.PaymentDao;
import com.TraineProject.CustomerService.entity.PaymentEntity;
import com.TraineProject.CustomerService.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentDaoImpl implements PaymentDao {

    private final PaymentRepository paymentRepository ;

    @Autowired
    public PaymentDaoImpl( PaymentRepository paymentRepository)
    {
        this.paymentRepository = paymentRepository ;
    }

    @Override
    public PaymentEntity savePayment(PaymentEntity paymentEntity) {

       return  paymentRepository.save(paymentEntity);
    }

    @Override
    public Optional<PaymentEntity> findByCustomerId (UUID customerId )
    {
        return paymentRepository.findByCustomerId( customerId );
    }

}
