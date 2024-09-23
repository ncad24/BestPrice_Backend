package com.upc.trabajoarquitectura.controller;

import com.upc.trabajoarquitectura.dtos.ProductoDTO;
import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.interfaces.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        ModelMapper mapper = new ModelMapper();
        try {
            List<Producto> productos = productoService.listarProductos();
            List<ProductoDTO> productoDTO = Arrays.asList(mapper.map(productos, ProductoDTO[].class));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/producto")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDTO >registrarProducto(@ModelAttribute ProductoDTO productoDTO,
                                         @RequestParam("imagen") MultipartFile imagen,
                                         @RequestParam Long categoriaID,
                                         @RequestParam Long marcaID) throws Exception {

        ModelMapper mapper = new ModelMapper();
        try{
            Producto producto = mapper.map(productoDTO, Producto.class);

            // Verificar si el archivo de imagen no está vacío
            if (!imagen.isEmpty()) {
                // Definir la ruta donde se va a guardar la imagen
                String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
                String rutaDirectorio = "C:/Arquitectura de Aplicaciones/trabajoarquitectura/src/main/java/com/upc/trabajoarquitectura/imagenes/producto/";
                String rutaCompleta = rutaDirectorio + nombreArchivo;

                // Guardar el archivo en el sistema de archivos
                File archivo = new File(rutaCompleta);
                imagen.transferTo(archivo);

                // Asignar la ruta de la imagen al producto
                producto.setRutaimagen(rutaCompleta);
            }

            producto = productoService.registrarProducto(producto, marcaID, categoriaID);
            productoDTO = mapper.map(producto, ProductoDTO.class);
            return new ResponseEntity<>(productoDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/producto/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoDTO productoDTO ){
        ModelMapper mapper = new ModelMapper();
        try {
            Producto producto = mapper.map(productoDTO, Producto.class);
            producto = productoService.actualizarProducto(producto);
            productoDTO = mapper.map(producto, ProductoDTO.class);
        }
        catch (Exception e){
            return new ResponseEntity<>(productoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productoDTO);
    }
    @DeleteMapping("/producto/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarProducto(@PathVariable Long id) throws Exception {
        try {
            productoService.eliminarProducto(id);
        }catch(Exception e){
            throw new Exception("Disculpe la molestia");
        }
    }

    @GetMapping("/productos/buscarNombre/{nombreProducto}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDTO>> listarProductoPorNombre(@PathVariable String nombreProducto){
        ModelMapper mapper = new ModelMapper();
        try{
            List<Producto> productos = productoService.encontrarPorNombreProducto(nombreProducto);
            List<ProductoDTO> productoDTO = Arrays.asList(mapper.map(productos, ProductoDTO[].class));
            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productos/buscarCategoria/{nombreCategoria}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDTO>> listarProductoPorCategoria(@PathVariable String nombreCategoria){
        ModelMapper mapper = new ModelMapper();
        try{
            List<Producto> productos = productoService.encontrarPorCategoriaProducto(nombreCategoria);
            List<ProductoDTO> productoDTO = Arrays.asList(mapper.map(productos, ProductoDTO[].class));
            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productos/buscarMarca/{nombreMarca}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDTO>> listarProductoPorMarca(@PathVariable String nombreMarca){
        ModelMapper mapper = new ModelMapper();
        try{
            List<Producto> productos = productoService.encontrarPorMarcaProducto(nombreMarca);
            List<ProductoDTO> productoDTO = Arrays.asList(mapper.map(productos, ProductoDTO[].class));
            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
