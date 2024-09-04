package com.upc.trabajoarquitectura.respository;


import com.upc.trabajoarquitectura.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    //Querys
}
