package com.sahin.inventoryservice.client;

import com.sahin.inventoryservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface APIClient {
    @GetMapping("/api/product/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
