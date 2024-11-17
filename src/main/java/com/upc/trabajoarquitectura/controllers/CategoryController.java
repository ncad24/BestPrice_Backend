package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.CategoryDTO;
import com.upc.trabajoarquitectura.entities.Category;
import com.upc.trabajoarquitectura.interfaces.ICategoryService;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CommonsLog
@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CategoryDTO> getCategory(){
        ModelMapper mapper = new ModelMapper();
        List<Category> categories = categoryService.getCategories();
        List<CategoryDTO> categoryDTO = Arrays.asList(mapper.map(categories, CategoryDTO[].class));
        return categoryDTO;
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDTO registerCategory(@RequestBody CategoryDTO categoryDTO){
        ModelMapper mapper = new ModelMapper();
        Category category = mapper.map(categoryDTO, Category.class);
        category = categoryService.registerCategory(category);
        categoryDTO = mapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @PutMapping("/category/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        ModelMapper mapper = new ModelMapper();
        Category category = mapper.map(categoryDTO, Category.class);
        category = categoryService.updateCategory(category);
        categoryDTO = mapper.map(category, CategoryDTO.class);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/category/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id) throws Exception{
        categoryService.deleteCategory(id);
    }

    @GetMapping("/category/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/category/name/{categoryName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> findByCategoryName(@PathVariable String categoryName) {
        return categoryService.findByNameStartsWith(categoryName);
    }

}
