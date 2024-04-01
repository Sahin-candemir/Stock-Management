package com.sahin.ProductCatalog.repository;

import com.sahin.ProductCatalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameIn(List<String> names);

}
