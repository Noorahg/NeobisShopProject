package com.example.NeobisShopProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productName;
    private Double productPrice;
    private String description;
    private int quantity;
}
