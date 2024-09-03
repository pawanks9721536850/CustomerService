package com.TraineProject.CustomerService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "customerId", nullable = false)
    private UUID customerId;

    @Column(name = "orderId")
    private UUID orderId;

    @OneToMany(mappedBy = "cartEntity")
    private List<ProductEntity> products;

}
