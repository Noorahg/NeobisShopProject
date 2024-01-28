package com.example.NeobisShopProject.controller;

import com.example.NeobisShopProject.dto.OrderDetailsDto;
import com.example.NeobisShopProject.service.Impl.OrderDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {

    private final OrderDetailsServiceImpl orderDetailsServiceImpl;

    @Autowired
    public OrderDetailsController(OrderDetailsServiceImpl orderDetailsServiceImpl) {
        this.orderDetailsServiceImpl = orderDetailsServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDetailsDto> createOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) {
        OrderDetailsDto createdOrderDetailsDto = orderDetailsServiceImpl.createOrderDetails(orderDetailsDto);
        return new ResponseEntity<>(createdOrderDetailsDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetailsDto>> getAllOrderDetails() {
        List<OrderDetailsDto> orderDetailsList = orderDetailsServiceImpl.getAllOrderDetails();
        return new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetailsById(@PathVariable Long id) {
        OrderDetailsDto orderDetailsDto = orderDetailsServiceImpl.getOrderDetailsById(id);
        return new ResponseEntity<>(orderDetailsDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id) {
        orderDetailsServiceImpl.deleteOrderDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
