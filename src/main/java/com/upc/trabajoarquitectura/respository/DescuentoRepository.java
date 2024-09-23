package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
}
