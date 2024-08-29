package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.interfaces.IProductoService;
import com.upc.trabajoarquitectura.respository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> listarTodosLosProductos()
    {
        return productoRepository.findAll();
    }
    @Override
    public Producto registrar(Producto producto){
        return productoRepository.save(producto);
    }
    @Override
    public void eliminar(Long id) throws Exception{
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }else {
            throw new Exception("No se puede eliminar el producto");
        }
    }
    @Override
    public Producto actualizar(Producto producto) {
        if (productoRepository.findById(producto.getProductoID()).isPresent()){
            return productoRepository.save(producto);
        }
        return null;
    }
}
