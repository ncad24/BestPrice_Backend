package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarTodosLosProductos();
    public Producto registrar(Producto producto);
    public Producto actualizar(Producto producto);
    public void eliminar(Long id) throws Exception;
}
