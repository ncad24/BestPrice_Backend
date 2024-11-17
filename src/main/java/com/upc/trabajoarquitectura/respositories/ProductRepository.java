package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Queries
    //Búsqueda de prodcutos por id
    public Product findByProductId(Long productId);
    //Búsqueda de productos por nombre
    public List<Product> findByNameStartingWithIgnoreCase(String productName);
    //Búsqueda de productos por marca
    public List<Product> findByBrand_Name(String brandName);
    //Búsqueda de productos por categoria
    public List<Product> findByCategory_Name(String categoryName);
    //Busqueda multiple
    public List<Product> findByBrand_NameAndCategory_Name(String brandName, String categoryName);
    boolean existsByName(String name);
    @Query("SELECT COUNT(p) FROM Product p")
    Long countTotalProduct();
}
