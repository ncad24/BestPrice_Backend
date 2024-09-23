package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercado;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercadoID;
import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoxSupermercadoRepository extends JpaRepository<ProductoxSupermercado, ProductoxSupermercadoID> {
    //Queries
    //Query para registrar
    ProductoxSupermercado findByPrimaryKey_Producto_ProductoIDAndPrimaryKey_Supermercado_SupermercadoID(Long productoId, Long supermercadoID);

    //Listar precios de un producto en varias tiendas
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO(pxs.primaryKey.supermercado.nombre, pxs.precio) FROM ProductoxSupermercado pxs WHERE pxs.primaryKey.producto.productoID = :idProduct")
    List<ProductoSupermercadoDTO> findPricesProductsForEachSupermarket(@Param("idProduct") Long idProduct);

    //Listar productos con menor precio por supermercado para mostrar en front
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO(pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre, MIN(pxs.precio)) FROM ProductoxSupermercado pxs GROUP BY pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre")
    List<PrecioProductosSupermercadoDTO> findMinPricesForProductsBySupermarket();

    //Listar productos con menor precio por supermercado para mostrar en front
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO(pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre, MIN(pxs.precio)) FROM ProductoxSupermercado pxs WHERE pxs.precio BETWEEN :precioMin AND :precioMax GROUP BY pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre")
    List<PrecioProductosSupermercadoDTO> findMinPricesForProductsBySupermarketBetweenPrices(@Param("precioMin") double precioMin, @Param("precioMax") double precioMax);

    //Listar productos con menor precio por supermercado para mostrar en front ordenados por menor precio
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO(pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre, MIN(pxs.precio)) FROM ProductoxSupermercado pxs " +
            "GROUP BY pxs.primaryKey.producto.productoID, pxs.primaryKey.producto.nombre " +
            "ORDER BY MIN(pxs.precio) ASC")
    List<PrecioProductosSupermercadoDTO> findMinPricesForProductsBySupermarketOrderAsc();
}
