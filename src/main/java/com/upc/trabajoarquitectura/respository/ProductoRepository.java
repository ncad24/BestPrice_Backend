package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
