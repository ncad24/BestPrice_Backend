package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Category;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.ICategoryService;
import com.upc.trabajoarquitectura.respositories.CategoryRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void verifyCategory(Category category){
        validationService.verifyNoEmpty(category.getName(),category.getDescription());
        validationService.verifyOnlyLetters(category.getName(),category.getDescription());
        validationService.verifyLength(category.getName(),3,50);
        validationService.verifyLength(category.getDescription(),10,255);
    }

    @Transactional
    public Category registerCategory(Category category){
        verifyCategory(category);
        validationService.checkForDuplicate(categoryRepository.existsByName(category.getName()), "Categoria");
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Category category){
        Category existingCategory = categoryRepository.findById(category.getCategoryId())
                .orElseThrow(() -> new RequestException("E-006", HttpStatus.NOT_FOUND, "La categoría a actualizar no existe"));

        // Si el nombre ha cambiado, entonces verificar duplicados
        if (!existingCategory.getName().equals(category.getName())) {
            validationService.checkForDuplicate(categoryRepository.existsByName(category.getName()), "Categoría");
        }

        // Luego puedes verificar los otros campos
        verifyCategory(category);
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId){
        if (categoryRepository.existsById(categoryId)){
            categoryRepository.deleteById(categoryId);
        }else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND, "La categoría a eliminar no existe");
        }
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Category> findByNameStartsWith(String categoryName) {
        return categoryRepository.findByNameStartingWithIgnoreCase(categoryName);
    }
}
