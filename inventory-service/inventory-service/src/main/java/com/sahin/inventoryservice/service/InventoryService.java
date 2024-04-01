package com.sahin.inventoryservice.service;

import com.sahin.inventoryservice.client.APIClient;
import com.sahin.inventoryservice.dto.CreateInventoryDto;
import com.sahin.inventoryservice.dto.InventoryResponse;
import com.sahin.inventoryservice.dto.ProductDto;
import com.sahin.inventoryservice.dto.UpdateInventoryDto;
import com.sahin.inventoryservice.exception.ResourceNotFoundException;
import com.sahin.inventoryservice.model.Inventory;
import com.sahin.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final APIClient apiClient;
    public InventoryResponse createInventory(CreateInventoryDto createInventoryDto) {

        ProductDto productDto = apiClient.getProductById(createInventoryDto.getProductId());

        Inventory inventory = new Inventory();
        inventory.setAmount(createInventoryDto.getAmount());
        inventory.setProductId(createInventoryDto.getProductId());

        Inventory saveInventory = inventoryRepository.save(inventory);

        return InventoryResponse.builder()
                .productName(productDto.getName())
                .stockAmount(createInventoryDto.getAmount())
                .build();
    }

    public InventoryResponse updateInventory(UpdateInventoryDto updateInventoryDto) {
        Inventory inventory = inventoryRepository.findByProductId(updateInventoryDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("No Product with this productId was found in stock. product id: "+updateInventoryDto.getProductId()));
        ProductDto productDto = apiClient.getProductById(updateInventoryDto.getProductId());
        double newAmount = inventory.getAmount() + updateInventoryDto.getChangeAmount();
        inventory.setAmount(newAmount);

        inventoryRepository.save(inventory);

        return InventoryResponse.builder()
                .productName(productDto.getName())
                .stockAmount(inventory.getAmount())
                .build();
    }

    public InventoryResponse getStockByProductId(Long productId) {
        ProductDto productDto = apiClient.getProductById(productId);

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No Product with this productId was found in stock. product id: "+productId));
        return InventoryResponse.builder()
                .productName(productDto.getName())
                .stockAmount(inventory.getAmount())
                .build();
    }
}
