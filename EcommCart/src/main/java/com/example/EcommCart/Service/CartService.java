package com.example.EcommCart.Service;

import com.example.EcommCart.Dtos.CartRequest;
import com.example.EcommCart.Dtos.CartResponse;
import com.example.EcommCart.Mapper.CartMapper;
import com.example.EcommCart.Model.Cart;
import com.example.EcommCart.Repository.CartsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartsRepo cartsRepo;

    private final CartMapper cartMapper = new CartMapper();

    public CartResponse addCart(CartRequest cartRequest) {
        Cart cart = cartMapper.mapToCart(cartRequest);
        cart = cartsRepo.save(cart);
        return cartMapper.mapToCartResponse(cart);
    }

    public CartResponse findCartById(int cartId) throws Exception{
        Cart cart = cartsRepo.findById(cartId).orElseThrow(() -> new Exception("Cart not found"));
        return cartMapper.mapToCartResponse(cart);
    }

    public CartResponse findCartByUserId(String userId){
        Cart cart = cartsRepo.findByUserId(userId);
        return cartMapper.mapToCartResponse(cart);
    }

    public CartResponse updateCart(CartRequest cartRequest, int id) throws Exception{
        Cart cart = cartsRepo.findById(id).orElseThrow(() -> new Exception("Cart not found"));
        Cart newCart = cartMapper.mapToCart(cartRequest);
        newCart.setId(cart.getId());
        cartsRepo.delete(cart);
        newCart = cartsRepo.save(newCart);
        return cartMapper.mapToCartResponse(newCart);
    }

    public CartResponse deleteCart(int id) throws Exception {
        Cart cart = cartsRepo.findById(id).orElseThrow(() -> new Exception("Cart not found"));
        cartsRepo.delete(cart);
        return cartMapper.mapToCartResponse(cart);
    }
}
