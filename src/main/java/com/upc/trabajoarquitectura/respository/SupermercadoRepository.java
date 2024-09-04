package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {
    //Querys
}
