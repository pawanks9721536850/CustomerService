package com.TraineProject.CustomerService.service;

import com.TraineProject.CustomerService.dto.ReceiptDto;

import java.util.UUID;

public interface ReceiptService {
    ReceiptDto receiptGenerator (UUID customerId );
}
