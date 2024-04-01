package com.sahin.ProductCatalog.service;

import com.sahin.ProductCatalog.dto.CategoryDto;
import com.sahin.ProductCatalog.exception.ResourceNotFoundException;
import com.sahin.ProductCatalog.model.Category;
import com.sahin.ProductCatalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);

        return mapper.map(saveCategory, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id : "+ id));
        return mapper.map(category, CategoryDto.class);
    }

    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id : "+ id));
        categoryRepository.delete(category);
    }

    public CategoryDto updateCategoryById(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id : "+ id));
        category.setName(categoryDto.getName());
        Category newCategory = categoryRepository.save(category);

        return mapper.map(newCategory, CategoryDto.class);
    }

    public CategoryDto getCategoryByCategoryName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with Name : "+ name));
        return mapper.map(category, CategoryDto.class);
    }
    public List<Category> getCategoriesByCategoryNameList(List<String> names){

        return categoryRepository.findByNameIn(names);
    }
}

