package com.upc.trabajoarquitectura.controller;



import com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoxSupermercadoDTO;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercado;
import com.upc.trabajoarquitectura.servicies.ProductoxSupermercadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoxSupermercadoController {
    @Autowired
    private ProductoxSupermercadoService productoxSupermercadoService;

    @GetMapping("/productos/supermercado")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductoxSupermercadoDTO> obtenerProductoxSupermercado() {
        ModelMapper mapper = new ModelMapper();
        List<ProductoxSupermercado> productoxSupermercados = productoxSupermercadoService.listarProductoxSupermercado();
        List<ProductoxSupermercadoDTO> productoxSupermercadoDTOS = Arrays.asList(mapper.map(productoxSupermercados, ProductoxSupermercadoDTO[].class));
        return productoxSupermercadoDTOS;
    }

    @PostMapping("/producto/supermercado/{productoID}/{supermercadoID}/{descuentoID}/{precio}")
    @PreAuthorize("hasRole('ADMIN')")
    public void registrarProudctoxSupermercado(@PathVariable Long productoID, @PathVariable Long supermercadoID, @PathVariable Long descuentoID, @PathVariable double precio){
        productoxSupermercadoService.registrarProductoxSupermercado(productoID, supermercadoID, descuentoID, precio);
    }

    @GetMapping("/productos/supermercados/precios/{idProduct}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductoSupermercadoDTO> encontrarPreciosProductosPorSupermercado(@PathVariable Long idProduct){
        return productoxSupermercadoService.encontrarPrecioDeProductoPorSupermercado(idProduct);
    }
    @GetMapping("/productos/supermercados/preciosProductos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorProductos(){
        return productoxSupermercadoService.encontrarPrecioMenorDeProductos();
    }
    @GetMapping("/productos/supermercados/preciosProductos/ascendente")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorProductosAscendente(){
        return productoxSupermercadoService.encontrarPrecioMenorDeProductosOrdenadoAsc();
    }
    @GetMapping("/productos/supermercados/preciosProductos/{precioMin}/{precioMax}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PrecioProductosSupermercadoDTO> encontrarProductosEntrePreciosMaxMin(@PathVariable double precioMin, @PathVariable double precioMax){
        return productoxSupermercadoService.encontrarPrecioDeProductoEntrePrecios(precioMin, precioMax);
    }
}
