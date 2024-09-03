package com.TraineProject.CustomerService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receipt")
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;

    @Column(name = "amount")
    private Double amount;

    @OneToOne
    @JoinColumn(name = "paymentId")
    private PaymentEntity paymentEntity;

}