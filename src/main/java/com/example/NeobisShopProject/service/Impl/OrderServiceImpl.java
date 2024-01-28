package com.example.NeobisShopProject.service.Impl;

import com.example.NeobisShopProject.dto.OrderDto;
import com.example.NeobisShopProject.entity.Order;
import com.example.NeobisShopProject.repository.OrderRepository;
import com.example.NeobisShopProject.service.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        // Обновляем поля заказа
        orderToUpdate.setTotalPrice(orderDto.getTotalPrice());
        orderToUpdate.setAddress(orderDto.getAddress());
        orderToUpdate.setPurchaseType(orderDto.getPurchaseType());

        Order updatedOrder = orderRepository.save(orderToUpdate);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}
