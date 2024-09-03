package com.TraineProject.CustomerService.controller;

import com.TraineProject.CustomerService.dto.AddressDto;
import com.TraineProject.CustomerService.dto.CheckoutResponseDto;
import com.TraineProject.CustomerService.dto.ProductDto;
import com.TraineProject.CustomerService.service.CartService;
import com.TraineProject.CustomerService.service.serviceImpl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {

    private final CartService cartService;
    private final TokenService tokenService;

    @Autowired
    public CartController(CartService cartService, TokenService tokenService) {
        this.cartService = cartService;
        this.tokenService = tokenService;
    }

    @PostMapping(path = "/addToCart")
    public String addToCart(@RequestHeader("TokenValidation") String token, @RequestBody ProductDto productDto, @RequestHeader("customerId") UUID customerId) throws Exception {
        try {
            boolean validToken = tokenService.isValidToken(token, customerId);

        } catch (Exception ex) {
            return ex.getMessage();
        }
        cartService.addToCart(productDto, customerId);

        return "product added to cart";
    }


    @PostMapping(path = "/checkout")
    public String checkOut(@RequestHeader("TokenValidation") String token, @RequestBody AddressDto addressDto, @RequestHeader("customerId") UUID customerId) throws Exception {
        try {
            boolean validToken = tokenService.isValidToken(token, customerId);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        CheckoutResponseDto checkoutResponseDto = cartService.checkOut(customerId , addressDto );

        if ( checkoutResponseDto != null &&  checkoutResponseDto.getOrderId()!= null )
        {
            String message = String.format(
                    "proceed for payment \n Order id : %s \n total amount : %f ",
                    checkoutResponseDto.getOrderId(), checkoutResponseDto.getTotalPrice()
            );
            return message ;
        }
        return "failed to checkout";
    }


}
