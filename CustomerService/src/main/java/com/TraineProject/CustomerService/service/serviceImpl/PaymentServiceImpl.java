package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.CartDao;
import com.TraineProject.CustomerService.dao.InvoiceDao;
import com.TraineProject.CustomerService.dao.PaymentDao;
import com.TraineProject.CustomerService.dto.PaymentDto;
import com.TraineProject.CustomerService.entity.CartEntity;
import com.TraineProject.CustomerService.entity.InvoiceEntity;
import com.TraineProject.CustomerService.entity.PaymentEntity;
import com.TraineProject.CustomerService.entity.ProductEntity;
import com.TraineProject.CustomerService.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final CartDao cartDao ;
    private final PaymentDao paymentDao ;
    private final InvoiceDao invoiceDao;
    public PaymentServiceImpl ( CartDao cartDao , PaymentDao paymentDao , InvoiceDao invoiceDao  )
    {
        this.cartDao = cartDao ;
        this.paymentDao = paymentDao ;
        this.invoiceDao = invoiceDao ;
    }

    public double addPrice ( List<ProductEntity> productList )
    {
        double totalAmount =0 ;
        for ( ProductEntity productEntity : productList )
        {
            totalAmount += (productEntity.getPrice()* productEntity.getQuantity());
        }
        return totalAmount;
    }

   public String generateInvoiceNumber()
   {
       Random random = new Random();
       long randomLong = random.nextLong();
       return Long.toString(randomLong);
   }

    @Override
    public String paymentProcess ( PaymentDto paymentDto )
    {
        if ( paymentDto.getCustomerId() == null )return "Customer Id not found";
        Optional<CartEntity> optionalCartEntity = cartDao.findCartByCustomerId(paymentDto.getCustomerId());
        CartEntity cartEntity;

        if (optionalCartEntity.isPresent())
        {
            cartEntity = optionalCartEntity.get() ;
        }
        else
        {
            return "Cart is empty" ;
        }

        List<ProductEntity> productList = cartDao.findProductsByCartId(cartEntity.getId());
        double totalAmount = addPrice ( productList );

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCustomerId(paymentDto.getCustomerId());
        paymentEntity.setCardNumber(paymentDto.getCardNumber());
        paymentEntity.setCvv(paymentDto.getCvv());
        paymentEntity.setExpiryDate(paymentDto.getExpiryDate());
        paymentEntity.setAmount(totalAmount);

        if ( paymentDto.getPaymentDate() != null )
        {
           paymentEntity.setPaymentDate( paymentDto.getPaymentDate() ) ;
        }
        else {
            paymentEntity.setPaymentDate ( LocalDateTime.now() );
        }


        paymentEntity.setOrderId(cartDao.findOrderIdByCustomerId(paymentDto.getCustomerId()));
        paymentDao.savePayment(paymentEntity);

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setCustomerId(paymentDto.getCustomerId());
        invoiceEntity.setTotalAmount(totalAmount);
        invoiceEntity.setInvoiceNumber(generateInvoiceNumber());
        invoiceEntity.setInvoiceDate(paymentEntity.getPaymentDate());

        InvoiceEntity invoice = invoiceDao.saveInvoice(invoiceEntity);
        return invoice.getId().toString() ;

    }

}
