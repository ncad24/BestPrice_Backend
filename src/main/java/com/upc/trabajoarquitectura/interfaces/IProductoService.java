package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public Producto registrarProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public void eliminarProducto(Long productoID) throws Exception;
    public void registrarSupermercadoProducto(Long productoID, Long supermercadoID);
    public void registrarDistritoProducto(Long productoID, Long distritoID);
}
