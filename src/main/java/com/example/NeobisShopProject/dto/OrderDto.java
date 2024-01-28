package com.example.NeobisShopProject.dto;

import com.example.NeobisShopProject.enums.PurchaseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long orderId;
    private double totalPrice;
    private String address;
    private PurchaseType purchaseType;

}
