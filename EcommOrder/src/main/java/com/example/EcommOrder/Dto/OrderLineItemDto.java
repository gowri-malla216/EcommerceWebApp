package com.example.EcommOrder.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {
    private int id;
    private String productId;
    private String title;
    private int quantity;
    private BigDecimal price;
}
