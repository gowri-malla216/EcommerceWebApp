package com.example.EcommCart.Mapper;

import com.example.EcommCart.Dtos.CartRequest;
import com.example.EcommCart.Dtos.CartResponse;
import com.example.EcommCart.Dtos.OrderLineItemDto;
import com.example.EcommCart.Model.Cart;
import com.example.EcommCart.Model.OrderLineItem;

import java.math.BigDecimal;
import java.util.List;

public class CartMapper {
    public Cart mapToCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        cart.setItems(cartRequest.getItems().stream().map(this::toOrderLineItem).toList());
        cart.setTotalPrice(calculateTotalPrice(cartRequest.getItems()));
        cart.setUserId(cartRequest.getUserId());
        return cart;
    }

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setItems(cart.getItems().stream().map(this::toOrderLineItemDto).toList());
        cartResponse.setId(cart.getId());
        cartResponse.setUserId(cart.getUserId());
        cartResponse.setTotalPrice(calculateTotalPrice(cartResponse.getItems()));
        return cartResponse;
    }

    private OrderLineItemDto toOrderLineItemDto(OrderLineItem orderLineItem) {
        OrderLineItemDto orderLineItemDto = new OrderLineItemDto();
        orderLineItemDto.setId(orderLineItem.getId());
        orderLineItemDto.setQuantity(orderLineItem.getQuantity());
        orderLineItemDto.setPrice(orderLineItem.getPrice());
        orderLineItemDto.setProductId(orderLineItem.getProductId());
        orderLineItemDto.setTitle(orderLineItem.getTitle());
        return orderLineItemDto;
    }

    private BigDecimal calculateTotalPrice(List<OrderLineItemDto> orderLineItemDtos) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(OrderLineItemDto orderLineItemDto: orderLineItemDtos){
            totalPrice = totalPrice.add(orderLineItemDto.getPrice());
        }
        return totalPrice;
    }

    public OrderLineItem toOrderLineItem(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setTitle(orderLineItemDto.getTitle());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setProductId(orderLineItemDto.getProductId());
        return orderLineItem;
    }
}
