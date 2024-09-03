package com.TraineProject.CustomerService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id ;
    @Column(name = "invoiceDate")
    private LocalDateTime invoiceDate;

    @Column(name = "invoiceNumber")
    private  String invoiceNumber ;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;
}