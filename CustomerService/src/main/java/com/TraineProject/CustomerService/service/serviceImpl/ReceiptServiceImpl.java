package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.PaymentDao;
import com.TraineProject.CustomerService.dao.ReceiptDao;
import com.TraineProject.CustomerService.dto.ReceiptDto;
import com.TraineProject.CustomerService.entity.PaymentEntity;
import com.TraineProject.CustomerService.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ReceiptServiceImpl implements ReceiptService {

     private final ReceiptDao receiptDao;
     private final PaymentDao paymentDao;

     @Autowired
     public ReceiptServiceImpl ( ReceiptDao receiptDao , PaymentDao paymentDao )
     {
         this.receiptDao=receiptDao ;
         this.paymentDao=paymentDao;

     }

    public String receiptNumberGenerator()
    {
        Random random = new Random();
        long randomLong = random.nextLong();
        return Long.toString(randomLong);
    }
    public ReceiptDto receiptGenerator (UUID customerId ){
           Optional<PaymentEntity> optionalPaymentEntity = paymentDao.findByCustomerId( customerId );
        if (!optionalPaymentEntity.isPresent()) {
            throw new RuntimeException("Payment not found for customer ID: " + customerId);
        }

        PaymentEntity paymentEntity = optionalPaymentEntity.get() ;

        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setOrderId(paymentEntity.getOrderId());
        receiptDto.setAmount(paymentEntity.getAmount());
        receiptDto.setReceiptNumber(receiptNumberGenerator() );
        receiptDto.setPaymentDate(paymentEntity.getPaymentDate());

        return receiptDto ;
    }


}
