package com.sahin.ProductCatalog.controller;

import com.sahin.ProductCatalog.dto.CreateProductDto;
import com.sahin.ProductCatalog.dto.ProductDto;
import com.sahin.ProductCatalog.dto.ProductIdListDto;
import com.sahin.ProductCatalog.dto.UpdateProductDto;
import com.sahin.ProductCatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long categoryId, @RequestBody CreateProductDto createProductDto){
        return new ResponseEntity<>(productService.createProduct(categoryId,createProductDto), HttpStatus.CREATED);
    }
    @GetMapping("/ca/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId), HttpStatus.OK);
    }
    @GetMapping("/byIdList")
    public  ResponseEntity<List<ProductDto>> getProductsByIdList(@RequestBody ProductIdListDto productIdListDto){
        return new ResponseEntity<>(productService.getProductsByIdList(productIdListDto), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>( "Product Deleted Success.",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id, @RequestBody UpdateProductDto updateProductDto) {
        return new ResponseEntity<>(productService.updateProductById(id, updateProductDto), HttpStatus.OK);
    }

}
