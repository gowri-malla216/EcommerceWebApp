package com.example.EcommOrder.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int orderId;
    private List<OrderLineItemDto> items;
    private String userId;
    private String orderStatus;
    private BigDecimal totalPrice;
}
