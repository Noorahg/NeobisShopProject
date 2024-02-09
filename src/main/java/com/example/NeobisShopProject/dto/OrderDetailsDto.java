package com.example.NeobisShopProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;
    private double amount;

}
