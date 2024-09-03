package com.TraineProject.CustomerService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @Column(name = "productName")
    private  String productName ;

    @Column (name = "price")
    private  Double price ;


    @Column (name = "quantity")
    private int quantity ;
    @Column(name ="productId", nullable = false)
    private UUID productId;

//    @OneToOne(mappedBy = "customer" )
//    @JoinColumn(name="id")
//    private CustomerEntity customerEntity ;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cartEntity ;
}
