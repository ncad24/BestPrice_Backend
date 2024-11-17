package com.upc.trabajoarquitectura.respositories;


import com.upc.trabajoarquitectura.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Querys
    public Category findByCategoryId(Long categoryId);
    public List<Category> findByNameStartingWithIgnoreCase(String categoryName);
    public boolean existsByName(String name);
}
