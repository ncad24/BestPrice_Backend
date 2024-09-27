package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Queries
    //Búsqueda de productos por nombre
    public List<Producto> findByNombreStartsWith(String nombreeProducto);
    //Búsqueda de productos por marca
    public List<Producto> findByMarca_Nombre(String marca);
    //Búsqueda de productos por categoria
    public List<Producto> findByCategoria_Nombre(String categoria);
    //Busqueda multiple
    public List<Producto> findByMarca_NombreAndCategoria_Nombre(String marca, String categoria);
}
