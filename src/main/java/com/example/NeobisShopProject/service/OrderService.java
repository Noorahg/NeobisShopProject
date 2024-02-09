package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long orderId);
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    void deleteOrder(Long orderId);
}