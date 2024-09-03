package com.TraineProject.CustomerService.dto;

import com.TraineProject.CustomerService.entity.ProductEntity;
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
public class CartDto {

    private UUID id;
    private UUID customerId;
    private  Double totalAmount ;
    private List<ProductDto> productList ;

//    public CartDto ( List<ProductDto> products )
//    {
//        this.productList = products ;
//    }


}
