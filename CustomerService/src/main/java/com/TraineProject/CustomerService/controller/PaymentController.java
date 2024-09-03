package com.TraineProject.CustomerService.controller;

import com.TraineProject.CustomerService.dto.PaymentDto;
import com.TraineProject.CustomerService.dto.ReceiptDto;
import com.TraineProject.CustomerService.service.PaymentService;
import com.TraineProject.CustomerService.service.ReceiptService;
import com.TraineProject.CustomerService.service.serviceImpl.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final ReceiptService receiptService ;
    private final TokenService tokenService;

    public PaymentController(TokenService tokenService, PaymentService paymentService , ReceiptService receiptService ) {
        this.tokenService = tokenService;
        this.paymentService = paymentService;
        this.receiptService= receiptService ;
    }

    @PostMapping(path = "/payment")
    public String paymentProcess(@RequestHeader("TokenValidation") String token, @RequestHeader("customerId") UUID customerId, @RequestBody PaymentDto paymentDto) throws Exception {
        try {
            boolean validToken = tokenService.isValidToken(token, customerId);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String invoiceId = paymentService.paymentProcess(paymentDto);

        String message = String.format("Proceed  Successful with \n Invoice Id : %s", invoiceId);
        return message;
    }

//    @GetMapping( path="/generateInvoice")
//    public String invoiceGenerator ()
//    {
//
//    }

    @GetMapping("/generateReceipt")
    public ReceiptDto receiptGenerator (@RequestHeader("customerId") UUID customerId, @RequestHeader("TokenValidation") String token) throws Exception
    {

            boolean validToken = tokenService.isValidToken(token, customerId);

            receiptService.receiptGenerator(customerId);

            return receiptService.receiptGenerator(customerId);

        }



}
