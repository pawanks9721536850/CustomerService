package com.TraineProject.CustomerService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "customerId", nullable = false, unique = true)
    private UUID customerId;

    @Column(name = "orderId")
    private UUID orderId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "cardNumber", nullable = false)
    @NotBlank(message = "card number should be filled")
    private String cardNumber;

    @Column(name = "cvv", nullable = false)
    @NotBlank(message = "cvv should be filled")
    private String cvv ;

    @Column(nullable = false)
    private String expiryDate;

    @Column(name = "paymentDate", nullable = false)
    private LocalDateTime paymentDate;

}
