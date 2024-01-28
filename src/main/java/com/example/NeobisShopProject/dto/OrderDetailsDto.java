package com.example.NeobisShopProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;
    private double amount;

}
