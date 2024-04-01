package com.sahin.ProductCatalog.controller;

import com.sahin.ProductCatalog.dto.CategoryDto;
import com.sahin.ProductCatalog.dto.CreateProductDto;
import com.sahin.ProductCatalog.dto.ProductDto;
import com.sahin.ProductCatalog.dto.UpdateProductDto;
import com.sahin.ProductCatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }
    @GetMapping("/category-name/{name}")
    public ResponseEntity<CategoryDto> getCategoryByCategoryName(@PathVariable("name") String name) {
        return new ResponseEntity<>(categoryService.getCategoryByCategoryName(name), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>( "Category Deleted Success.",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryDto), HttpStatus.OK);
    }
}
