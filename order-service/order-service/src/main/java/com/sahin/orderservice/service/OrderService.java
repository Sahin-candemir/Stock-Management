package com.sahin.orderservice.service;

import com.sahin.orderservice.client.APIClient;
import com.sahin.orderservice.dto.CreateOrderDto;
import com.sahin.orderservice.dto.OrderResponseDto;
import com.sahin.orderservice.dto.ProductDto;
import com.sahin.orderservice.dto.ProductIdListDto;
import com.sahin.orderservice.model.Order;
import com.sahin.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final APIClient apiClient;
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto) {
        ProductIdListDto productIdListDto = new ProductIdListDto();
        productIdListDto.setProductIds(createOrderDto.getProductIdlist());
        List<ProductDto> productDtos = apiClient.getProductsByIdList(productIdListDto);
        Order order = new Order();
        order.setUserId(createOrderDto.getUserId());
        order.setCreatedDate(LocalDateTime.now());
        order.setProductIdList(createOrderDto.getProductIdlist());
        Order saveOrder = orderRepository.save(order);
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderId(saveOrder.getId())
                .productDtoList(productDtos)
                .build();
        return null;
    }
}
