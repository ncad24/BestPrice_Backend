package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    //Querys
    public Supermarket findSupermarketBySupermarketId(Long id);
    public boolean existsByName(String supermarketName);
    public List<Supermarket> findByNameStartingWithIgnoreCase(String supermarketName);
    @Query("SELECT COUNT(s) FROM Supermarket s")
    Long countTotalSupermarkets();

}
