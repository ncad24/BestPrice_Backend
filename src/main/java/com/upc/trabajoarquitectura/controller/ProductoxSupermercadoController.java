package com.upc.trabajoarquitectura.controller;



import com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoxSupermercadoDTO;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercado;
import com.upc.trabajoarquitectura.servicies.ProductoxSupermercadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductoxSupermercadoDTO>> obtenerProductoxSupermercado() {
        try{
            ModelMapper mapper = new ModelMapper();
            List<ProductoxSupermercado> productoxSupermercados = productoxSupermercadoService.listarProductoxSupermercado();
            List<ProductoxSupermercadoDTO> productoxSupermercadoDTOS = Arrays.asList(mapper.map(productoxSupermercados, ProductoxSupermercadoDTO[].class));
            return new ResponseEntity<>(productoxSupermercadoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/producto/supermercado/{productoID}/{supermercadoID}/{descuentoID}/{precio}")
    @PreAuthorize("hasRole('ADMIN')")
    public void registrarProudctoxSupermercado(@PathVariable Long productoID, @PathVariable Long supermercadoID, @PathVariable Long descuentoID, @PathVariable double precio) throws Exception {
        try{
            productoxSupermercadoService.registrarProductoxSupermercado(productoID, supermercadoID, descuentoID, precio);
        }catch (Exception e){
            throw new Exception("No se pudo registrar");
        }
    }

    @GetMapping("/productos/supermercados/precios/{idProduct}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoSupermercadoDTO>> encontrarPreciosProductosPorSupermercado(@PathVariable Long idProduct){
        try{
            List<ProductoSupermercadoDTO> precios = productoxSupermercadoService.encontrarPrecioDeProductoPorSupermercado(idProduct);
            return ResponseEntity.ok(precios);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/productos/supermercados/preciosProductos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PrecioProductosSupermercadoDTO>> encontrarPrecioMenorProductos(){
        try {
            List<PrecioProductosSupermercadoDTO> precios = productoxSupermercadoService.encontrarPrecioMenorDeProductos();
            return ResponseEntity.ok(precios);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/productos/supermercados/preciosProductos/ascendente")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PrecioProductosSupermercadoDTO>> encontrarPrecioMenorProductosAscendente(){
        try {
            List<PrecioProductosSupermercadoDTO> precios = productoxSupermercadoService.encontrarPrecioMenorDeProductosOrdenadoAsc();
            return ResponseEntity.ok(precios);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/productos/supermercados/preciosProductos/{precioMin}/{precioMax}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PrecioProductosSupermercadoDTO>> encontrarProductosEntrePreciosMaxMin(@PathVariable double precioMin, @PathVariable double precioMax){
        try {
            List<PrecioProductosSupermercadoDTO> productos = productoxSupermercadoService.encontrarPrecioDeProductoEntrePrecios(precioMin, precioMax);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
