package com.sahin.orderservice.controller;

import com.sahin.orderservice.dto.CreateOrderDto;
import com.sahin.orderservice.dto.OrderResponseDto;
import com.sahin.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderDto createOrderDto){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED);
    }
}
