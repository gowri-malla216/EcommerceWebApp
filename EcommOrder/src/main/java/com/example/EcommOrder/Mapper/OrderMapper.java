package com.example.EcommOrder.Mapper;

import com.example.EcommOrder.Dto.CartResponse;
import com.example.EcommOrder.Dto.OrderLineItemDto;
import com.example.EcommOrder.Dto.OrderRequest;
import com.example.EcommOrder.Dto.OrderResponse;
import com.example.EcommOrder.Model.Order;
import com.example.EcommOrder.Model.OrderLineItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setItems(orderRequest.getItems().stream().map(this::mapToOrderLineItemsModel).toList());
        order.setTotalPrice(calculateTotalPrice(orderRequest));
        order.setUserId(orderRequest.getUserId());
        return order;
    }

    public Order toOrder(CartResponse cartResponse) {
        Order order = new Order();
        order.setItems(cartResponse.getItems().stream().map(this::mapToOrderLineItemsModel).toList());
        order.setTotalPrice(calculateTotalPrice(cartResponse));
        order.setUserId(cartResponse.getUserId());
        return order;
    }

    private BigDecimal calculateTotalPrice(CartResponse cartResponse) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderLineItemDto orderLineItemDto : cartResponse.getItems()) {
            totalPrice = totalPrice.add(orderLineItemDto.getPrice());
        }
        return totalPrice;
    }

    private BigDecimal calculateTotalPrice(OrderRequest orderRequest) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderLineItemDto orderLineItemDto : orderRequest.getItems()) {
            totalPrice = totalPrice.add(orderLineItemDto.getPrice());
        }
        return totalPrice;
    }

    private OrderLineItem mapToOrderLineItemsModel(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setProductId(orderLineItemDto.getProductId());
        orderLineItem.setTitle(orderLineItemDto.getTitle());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        return orderLineItem;
    }

    public OrderResponse toOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setItems(order.getItems().stream().map(this::mapToOrderLineItemDto).toList());
        orderResponse.setOrderStatus(order.getOrderStatus());
        orderResponse.setTotalPrice(order.getTotalPrice());
        return orderResponse;
    }

    private OrderLineItemDto mapToOrderLineItemDto(OrderLineItem orderLineItem) {
        OrderLineItemDto orderLineItemDto = new OrderLineItemDto();
        orderLineItemDto.setId(orderLineItem.getId());
        orderLineItemDto.setProductId(orderLineItem.getProductId());
        orderLineItemDto.setTitle(orderLineItem.getTitle());
        orderLineItemDto.setQuantity(orderLineItem.getQuantity());
        orderLineItemDto.setPrice(orderLineItem.getPrice());
        return orderLineItemDto;
    }
}
