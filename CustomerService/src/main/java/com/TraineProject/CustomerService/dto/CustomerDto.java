package com.TraineProject.CustomerService.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {


    @Column(name="name")
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "length of name must be 3 to 50")
    private String name ;

    @Column(name="email" , nullable =false , unique = true )
    @NotBlank(message = "email is required")
    private String email ;

    @Column(name="password")
    @NotBlank(message = "password is required")
    @Size(min = 8, max = 50, message = "length of password must be 8 to 50")
    private String password ;


}
