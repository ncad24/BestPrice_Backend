package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Distrito;
import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.entities.Supermercado;
import com.upc.trabajoarquitectura.interfaces.IProductoService;
import com.upc.trabajoarquitectura.respository.DistritoRepository;
import com.upc.trabajoarquitectura.respository.ProductoRepository;
import com.upc.trabajoarquitectura.respository.SupermercadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SupermercadoRepository supermercadoRepository;
    @Autowired
    private DistritoRepository distritoRepository;

    @Transactional
    public List<Producto> listarProductos()
    {
        return productoRepository.findAll();
    }
    @Transactional
    public Producto registrarProducto(Producto producto, Long marcaID, Long categoriaID, Long supermercadoID, Long distritoID) throws Exception {
        //Buscar marca, categoria, supermercado y distrito
        Marca marca = marcaRepository.findById(marcaID).orElseThrow(() -> new Exception ("Marca no encontrada"));
        Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(()-> new Exception ("Categoria no encontrada"));
        Supermercado supermercado = supermercadoRepository.findById(supermercadoID).orElseThrow(() -> new Exception ("Supermercado no encontrado"));
        Distrito distrito = distritoRepository.findById(distritoID).orElseThrow(() -> new Exception("Distrito no encontrado"));
        //Asignar
        producto.setMarcas(List.of(marca));
        producto.setCategorias(List.of(categoria));
        producto.getSupermercados().add(supermercado);
        producto.getDistritos().add(distrito);
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

    @Transactional
    public void grabarAsignacionSupermercadoProducto(Long productoID, Long supermercadoID){
        Producto xproducto = productoRepository.findById(productoID).get();
        Supermercado ySupermercado = supermercadoRepository.findById(supermercadoID).get();
        xproducto.getSupermercados().add(ySupermercado);
        ySupermercado.getSupermercadoProductos().add(xproducto);
        productoRepository.save(xproducto);
        supermercadoRepository.save(ySupermercado);
    }

    @Transactional
    public void grabarAsignacionDistritoProducto(Long productoID, Long distritoID){
        Producto xproducto = productoRepository.findById(productoID).get();
        Distrito yDistrito = distritoRepository.findById(distritoID).get();
        xproducto.getDistritos().add(yDistrito);
        yDistrito.getDistritoProductos().add(xproducto);
        productoRepository.save(xproducto);
        distritoRepository.save(yDistrito);
    }

}
