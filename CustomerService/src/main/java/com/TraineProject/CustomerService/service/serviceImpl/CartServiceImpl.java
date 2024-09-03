package com.TraineProject.CustomerService.service.serviceImpl;

import com.TraineProject.CustomerService.dao.AddressDao;
import com.TraineProject.CustomerService.dao.CartDao;
import com.TraineProject.CustomerService.dao.ProductDao;
import com.TraineProject.CustomerService.dto.AddressDto;
import com.TraineProject.CustomerService.dto.CartDto;
import com.TraineProject.CustomerService.dto.CheckoutResponseDto;
import com.TraineProject.CustomerService.dto.ProductDto;
import com.TraineProject.CustomerService.entity.AddressEntity;
import com.TraineProject.CustomerService.entity.CartEntity;
import com.TraineProject.CustomerService.entity.ProductEntity;
import com.TraineProject.CustomerService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartDao cartDao;
    private final ProductDao productDao;
    private final AddressDao addressDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao, ProductDao productDao, AddressDao addressDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
        this.addressDao = addressDao;
    }


    public CartDto changeToCartDto(CartEntity cartEntity) {
        List<ProductDto> productList = cartDao.findProductsByCartId(cartEntity.getId()).stream().map(
                productEntity -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(productEntity.getId());
                    productDto.setQuantity(productEntity.getQuantity());
                    return productDto;
                }
        ).collect(Collectors.toList());

        CartDto cartDto = new CartDto();
        cartDto.setId(cartEntity.getId());
        cartDto.setCustomerId(cartEntity.getCustomerId());
        cartDto.setProductList(productList);

        return cartDto;
    }

    public double addPrice(List<ProductEntity> productList) {
        double totalPrice = 0;
        for (ProductEntity productEntity : productList) {
            totalPrice += (productEntity.getPrice() * productEntity.getQuantity());
        }
        return totalPrice;
    }


    @Override
    public CartDto addToCart(ProductDto productDto, UUID customerId) {
        Optional<CartEntity> cart = cartDao.findCartByCustomerId(customerId);
        CartEntity cartEntity;

        if (cart.isPresent()) {
            cartEntity = cart.get();
        } else {
            cartEntity = new CartEntity();
            cartEntity.setCustomerId(customerId);
            cartEntity = cartDao.saveCart(cartEntity);
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCartEntity(cartEntity);
        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductId(productDto.getId());
        productEntity.setQuantity(productDto.getQuantity());
        productEntity.setPrice(productDto.getPrice());

        cartDao.saveProductToCart(productEntity);

        return changeToCartDto(cartEntity);

    }

    @Override
    public CheckoutResponseDto checkOut(UUID customerId, AddressDto addressDto) {

        Optional<CartEntity> optionalCartEntity = cartDao.findCartByCustomerId(customerId);
        CartEntity cartEntity;
        if (optionalCartEntity.isPresent()) {
            cartEntity = optionalCartEntity.get();
        } else {
            return null;
        }

        List<ProductEntity> productList = cartDao.findProductsByCartId(cartEntity.getId());
        double totalPrice = addPrice(productList);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDto.getCustomerID());
        addressEntity.setAddressLine1(addressDto.getAddressLine1());
        addressEntity.setLocality(addressDto.getLocality());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPincode(addressDto.getPincode());
        addressDao.saveAddress(addressEntity);

        UUID orderId = UUID.randomUUID();
        cartEntity.setOrderId(orderId);
        cartDao.saveCart(cartEntity);

        CheckoutResponseDto checkoutResponseDto = new CheckoutResponseDto();
        checkoutResponseDto.setOrderId(orderId);
        checkoutResponseDto.setTotalPrice(totalPrice);

        return checkoutResponseDto;

    }

}
