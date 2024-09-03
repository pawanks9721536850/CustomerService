package com.TraineProject.CustomerService.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

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

//    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL, orphanRemoval = true)
//    private AddressEntity addressEntity;

//    @OneToOne(mappedBy ="customer" )
//    private ProductEntity productEntity;

    private String token;

    private LocalDateTime tokenExpiration;

}
