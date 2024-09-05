package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import com.upc.trabajoarquitectura.entities.ProductoxUsuarioID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoxUsuarioRepository extends JpaRepository<ProductoxUsuario, ProductoxUsuarioID> {
    //Querys
    ProductoxUsuario findByPrimaryKey_Producto_ProductoIDAndPrimaryKey_Usuario_UsuarioID(Long productoId, Long usuarioId);
}
