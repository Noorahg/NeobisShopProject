package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.OrderDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderDetailsService {
    OrderDetailsDto createOrderDetails(OrderDetailsDto orderDetailsDto);
    List<OrderDetailsDto> getAllOrderDetails();
    OrderDetailsDto getOrderDetailsById(Long id);
    void deleteOrderDetails(Long id);
}
