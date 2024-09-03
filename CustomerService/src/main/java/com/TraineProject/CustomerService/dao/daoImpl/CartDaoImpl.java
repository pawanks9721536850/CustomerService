package com.TraineProject.CustomerService.dao.daoImpl;

import com.TraineProject.CustomerService.dao.CartDao;
import com.TraineProject.CustomerService.dto.PaymentDto;
import com.TraineProject.CustomerService.entity.CartEntity;
import com.TraineProject.CustomerService.entity.ProductEntity;
import com.TraineProject.CustomerService.repo.CartRepository;
import com.TraineProject.CustomerService.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartDaoImpl implements CartDao {

    private final CartRepository cartRepository ;
    private final ProductRepository productRepository;

    @Autowired
    public CartDaoImpl ( CartRepository cartRepository , ProductRepository productRepository)
    {
        this.cartRepository = cartRepository ;
        this.productRepository=productRepository;
    }

    @Override
    public CartEntity saveCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);

    }

    @Override
    public Optional<CartEntity> findCartByCustomerId(UUID customerId) {
        return cartRepository.findCartByCustomerId(customerId);
    }

    @Override
    public void saveProductToCart(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public Optional<CartEntity> getCartByCustomerId(UUID customerId)
    {
        return cartRepository.findCartByCustomerId(customerId);
    }

    @Override
    public List<ProductEntity> findProductsByCartId(UUID cartId) {
        return productRepository.findByCartEntity_Id(cartId);
    }

    @Override
    public UUID findOrderIdByCustomerId ( UUID customerId  )
    {
        Optional<CartEntity> optionalCartEntity = cartRepository.findCartByCustomerId(customerId );
        CartEntity cartEntity ;
        if (optionalCartEntity.isEmpty())
        {
            throw new RuntimeException("Order Id not found");
        }
        else
        {
            cartEntity = optionalCartEntity.get() ;
        }
        return cartEntity.getOrderId() ;
    }



}
