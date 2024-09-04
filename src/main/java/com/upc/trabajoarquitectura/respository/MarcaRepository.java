package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    //Querys
}
