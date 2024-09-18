package com.example.EcommOrder.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponse {
    private int Id;
    private List<OrderLineItemDto> items;
    private String userId;
    private BigDecimal totalPrice;
}
