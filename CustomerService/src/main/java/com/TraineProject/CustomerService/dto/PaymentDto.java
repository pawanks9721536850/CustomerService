package com.TraineProject.CustomerService.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private UUID customerId;

    @NotBlank
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 of digits")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    @NotBlank
    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

//    @NotNull(message = "Amount cannot be blank")
//    @Positive(message = "Amount must be positive")
//    private double amount;

    private LocalDateTime paymentDate;
}
