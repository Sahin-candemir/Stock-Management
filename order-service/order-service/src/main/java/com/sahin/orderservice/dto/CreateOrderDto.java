package com.sahin.orderservice.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDto {

    private Long userId;

    private List<Long> productIdlist;
}
