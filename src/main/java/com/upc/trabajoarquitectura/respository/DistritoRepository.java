package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {
    //Querys
}
