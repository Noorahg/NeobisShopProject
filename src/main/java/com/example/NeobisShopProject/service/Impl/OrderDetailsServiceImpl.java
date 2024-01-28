package com.example.NeobisShopProject.service.Impl;

import com.example.NeobisShopProject.dto.OrderDetailsDto;
import com.example.NeobisShopProject.entity.OrderDetails;
import com.example.NeobisShopProject.repository.OrderDetailsRepository;
import com.example.NeobisShopProject.service.OrderDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, ModelMapper modelMapper) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDetailsDto createOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
        return modelMapper.map(savedOrderDetails, OrderDetailsDto.class);
    }

    @Override
    public List<OrderDetailsDto> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        return orderDetailsList.stream()
                .map(orderDetails -> modelMapper.map(orderDetails, OrderDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDto getOrderDetailsById(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order details not found"));
        return modelMapper.map(orderDetails, OrderDetailsDto.class);
    }

    @Override
    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.deleteById(id);
    }

}
