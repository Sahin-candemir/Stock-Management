package com.sahin.ProductCatalog.service;

import com.sahin.ProductCatalog.dto.*;
import com.sahin.ProductCatalog.exception.ResourceNotFoundException;
import com.sahin.ProductCatalog.model.Category;
import com.sahin.ProductCatalog.model.Product;
import com.sahin.ProductCatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;
    public ProductDto createProduct(Long categoryId, CreateProductDto createProductDto) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        Category category = mapper.map(categoryDto, Category.class);
        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setDescription(createProductDto.getDescription());
        product.setPrice(createProductDto.getPrice());
        product.setCategory(category);

        Product saveProduct = productRepository.save(product);

        return mapper.map(saveProduct, ProductDto.class);
    }

    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Product Not Found with id : "+id)));
        return mapper.map(product, ProductDto.class);
    }

    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Product Not Found with id : "+id)));
        productRepository.delete(product);
    }

    public ProductDto updateProductById(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Product Not Found with id : "+id)));
        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());
        Product newProduct = productRepository.save(product);
        return mapper.map(newProduct, ProductDto.class);
    }

    public List<ProductDto> getProductsByIdList(ProductIdListDto productIdListDto) {
        List<Product> products = productRepository.findAllById(productIdListDto.getIds());

        return products.stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }
}
