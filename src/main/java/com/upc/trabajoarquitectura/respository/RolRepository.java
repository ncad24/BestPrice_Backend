package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    //Querys
    public Rol findByNombreRol(String nombreRol);
}