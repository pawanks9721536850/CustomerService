package com.TraineProject.CustomerService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @Column( name="addressLine1" )
    private String addressLine1 ;

    @Column( name="locality")
    private String locality ;

    @Column( name="city")
    private String city ;

    @Column( name = "state" )
    private String state ;

    @Column( name ="pincode")
    private String pincode ;



}