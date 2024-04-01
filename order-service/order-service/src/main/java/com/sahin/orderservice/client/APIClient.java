package com.sahin.orderservice.client;

import com.sahin.orderservice.dto.ProductDto;
import com.sahin.orderservice.dto.ProductIdListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface APIClient {
    @GetMapping("/byIdList")
    List<ProductDto> getProductsByIdList(@RequestBody ProductIdListDto productIdListDto);

}
