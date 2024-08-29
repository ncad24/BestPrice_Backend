package com.upc.trabajoarquitectura.controlador;

import com.upc.trabajoarquitectura.dtos.ProductoDTO;
import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.interfaces.IProductoService;
import com.upc.trabajoarquitectura.servicies.ProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public List<ProductoDTO> listarTodosLosProductos(){
        ModelMapper mapper = new ModelMapper();
        List<Producto> productos = productoService.listarTodosLosProductos();
        List<ProductoDTO> productoDTO = Arrays.asList(mapper.map(productos, ProductoDTO[].class));
        return productoDTO;
    }
    @PostMapping("/producto")
    public ProductoDTO registrar(@RequestBody ProductoDTO productoDTO){
        ModelMapper mapper = new ModelMapper();
        Producto producto;
        producto = mapper.map(productoDTO, Producto.class);
        producto = productoService.registrar(producto);
        productoDTO = mapper.map(producto, ProductoDTO.class);
        return productoDTO;
    }
    @DeleteMapping("/producto/eliminar/{id}")
    public void eliminar(@PathVariable Long id) throws Exception {
        try {
            productoService.eliminar(id);
        }catch(Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }
    @PutMapping("/producto/actualizar")
    public ResponseEntity<ProductoDTO> actualizar(@RequestBody ProductoDTO productoDTO){
        ModelMapper mapper = new ModelMapper();
        try {
            Producto producto = mapper.map(productoDTO, Producto.class);
            producto = productoService.actualizar(producto);
            productoDTO = mapper.map(producto, ProductoDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(productoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productoDTO);
    }
}
