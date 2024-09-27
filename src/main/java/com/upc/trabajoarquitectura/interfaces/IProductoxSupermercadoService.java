package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercado;

import java.util.List;

public interface IProductoxSupermercadoService {
    public List<ProductoxSupermercado> listarProductoxSupermercado ();
    public void registrarProductoxSupermercado(Long productoID, Long supermercadoID, Long descuentoID, double precio);

    public List<ProductoSupermercadoDTO> encontrarPrecioDeProductoPorSupermercado(Long idProduct);
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorDeProductos();
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorDeProductosOrdenadoAsc();
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioDeProductoEntrePrecios(double precioMin, double precioMax);
}
