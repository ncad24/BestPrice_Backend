package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public Producto registrarProducto(Producto producto,Long marcaID, Long categoriaID) throws Exception;
    public Producto actualizarProducto(Producto producto);
    public void eliminarProducto(Long productoID) throws Exception;

    public List<Producto> encontrarPorNombreProducto(String nombreProducto);
    public List<Producto> encontrarPorCategoriaProducto(String nombreCategoria);
    public List<Producto> encontrarPorMarcaProducto(String nombreMarca);
}
