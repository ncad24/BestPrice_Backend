package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    //Querys
}
