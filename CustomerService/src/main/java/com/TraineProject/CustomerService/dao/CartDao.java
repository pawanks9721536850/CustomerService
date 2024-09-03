package com.TraineProject.CustomerService.dao;

import com.TraineProject.CustomerService.dto.PaymentDto;
import com.TraineProject.CustomerService.entity.CartEntity;
import com.TraineProject.CustomerService.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartDao {
    //Optional<CartEntity> addToCart(ProductEntity productEntity);

    CartEntity saveCart(CartEntity cartEntity);

    Optional<CartEntity> findCartByCustomerId(UUID customerId);

    void saveProductToCart(ProductEntity productEntity);

    Optional<CartEntity> getCartByCustomerId(UUID customerId);

    List<ProductEntity> findProductsByCartId(UUID cartId );

    UUID findOrderIdByCustomerId (UUID customerId  );
}
