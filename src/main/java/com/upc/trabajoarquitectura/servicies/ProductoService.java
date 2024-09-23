package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.interfaces.IProductoService;
import com.upc.trabajoarquitectura.respository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<Producto> listarProductos()
    {
        return productoRepository.findAll();
    }
    @Transactional
    public Producto registrarProducto(Producto producto, Long marcaID, Long categoriaID) throws Exception {
        //Buscar marca, categoria
        Marca marca = marcaRepository.findById(marcaID).orElseThrow(() -> new Exception ("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(()-> new Exception ("Categoria no encontrada"));

        //Asignar
        producto.setMarca(marca);
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }
    @Transactional
    public Producto actualizarProducto(Producto producto) {
        if (productoRepository.findById(producto.getProductoID()).isPresent()){
            return productoRepository.save(producto);
        }
        return null;
    }
    @Transactional
    public void eliminarProducto(Long id) throws Exception{
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }else {
            throw new Exception("No se puede eliminar el producto");
        }
    }

    @Override
    public List<Producto> encontrarPorNombreProducto(String nombreProducto){
        return productoRepository.findByNombreStartsWith(nombreProducto);
    }
    @Override
    public List<Producto> encontrarPorCategoriaProducto(String nombreCategoria){
        return productoRepository.findByCategoria_Nombre(nombreCategoria);
    }
    @Override
    public List<Producto> encontrarPorMarcaProducto(String nombreMarca){
        return productoRepository.findByMarca_Nombre(nombreMarca);
    }
}
