package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Distrito;
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
    public Producto registrarProducto(Producto producto){
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
    public void registrarSupermercadoProducto(Long productoID, Long supermercadoID){
        Producto xproducto = productoRepository.findById(productoID).get();
        Supermercado ySupermercado = supermercadoRepository.findById(supermercadoID).get();
        xproducto.getSupermercados().add(ySupermercado);
        ySupermercado.getSupermercadoProductos().add(xproducto);
        productoRepository.save(xproducto);
        supermercadoRepository.save(ySupermercado);
    }

    @Transactional
    public void registrarDistritoProducto(Long productoID, Long distritoID){
        Producto xproducto = productoRepository.findById(productoID).get();
        Distrito yDistrito = distritoRepository.findById(distritoID).get();
        xproducto.getDistritos().add(yDistrito);
        yDistrito.getDistritoProductos().add(xproducto);
        productoRepository.save(xproducto);
        distritoRepository.save(yDistrito);
    }

}
