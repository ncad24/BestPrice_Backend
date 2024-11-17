package com.upc.trabajoarquitectura.interfaces;


import com.upc.trabajoarquitectura.entities.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> getCategories();
    public Category registerCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long categoryId) throws Exception;
    public Category findCategoryById(Long categoryId);
    public List<Category> findByNameStartsWith(String categoryName);
}
