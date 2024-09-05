package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import com.upc.trabajoarquitectura.entities.ProductoxUsuarioID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoxUsuarioRepository extends JpaRepository<ProductoxUsuario, ProductoxUsuarioID> {
    //Querys
    public ProductoxUsuario findByProductoIdAndUsuarioId(Long productoId, Long usuarioId);
}
