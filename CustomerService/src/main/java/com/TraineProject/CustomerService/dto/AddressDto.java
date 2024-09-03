package com.TraineProject.CustomerService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private UUID id;
    private UUID customerID;
    @NotBlank(message = "required address ")

    private String addressLine1;
    private String locality;
    @NotBlank(message = "required city  ")

    private String city;
    @NotBlank(message = "required State ")

    private String state;
    @NotBlank(message = "required pincode")
    private String pincode;
}
