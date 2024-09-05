package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public Producto registrarProducto(Producto producto,Long marcaID, Long categoriaID, Long supermercadoID, Long distritoID) throws Exception;
    public Producto actualizarProducto(Producto producto);
    public void eliminarProducto(Long productoID) throws Exception;
    //Se utiliza para modificar para asignar un producto a un supermercado, es decir actualizar
    public void grabarAsignacionSupermercadoProducto(Long productoID, Long supermercadoID);
    public void grabarAsignacionDistritoProducto(Long productoID, Long distritoID);
}
