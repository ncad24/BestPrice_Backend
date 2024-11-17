package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.ProductClick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface ProductClickRepository extends JpaRepository<ProductClick, Long> {
    public ProductClick findById(long id);
    @Query("SELECT p FROM ProductClick p ORDER BY p.clicks DESC")
    List<ProductClick> findTop5ByOrderByClicksDesc(Pageable pageable);
}
