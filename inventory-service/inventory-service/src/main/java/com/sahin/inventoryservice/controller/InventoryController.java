package com.sahin.inventoryservice.controller;

import com.sahin.inventoryservice.dto.CreateInventoryDto;
import com.sahin.inventoryservice.dto.InventoryResponse;
import com.sahin.inventoryservice.dto.UpdateInventoryDto;
import com.sahin.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody CreateInventoryDto createInventoryDto){
        return new ResponseEntity<>(inventoryService.createInventory(createInventoryDto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<InventoryResponse> updateInventory(@RequestBody UpdateInventoryDto updateInventoryDto){
        return new ResponseEntity<>(inventoryService.updateInventory(updateInventoryDto), HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getStockByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(inventoryService.getStockByProductId(productId), HttpStatus.OK);
    }

}
