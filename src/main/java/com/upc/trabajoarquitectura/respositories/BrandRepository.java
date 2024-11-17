package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    //Querys
    public Brand findByBrandId(Long brandId);
    public boolean existsByName(String name);
    public List<Brand> findByNameStartsWith(String name);
    @Query("SELECT COUNT(b) FROM Brand b")
    Long countTotalBrands();
}
