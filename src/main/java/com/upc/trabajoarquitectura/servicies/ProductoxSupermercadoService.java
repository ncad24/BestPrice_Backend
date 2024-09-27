package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.dtos.PrecioProductosSupermercadoDTO;
import com.upc.trabajoarquitectura.dtos.ProductoSupermercadoDTO;
import com.upc.trabajoarquitectura.entities.Descuento;
import com.upc.trabajoarquitectura.entities.ProductoxSupermercado;
import com.upc.trabajoarquitectura.interfaces.IProductoxSupermercadoService;
import com.upc.trabajoarquitectura.respository.DescuentoRepository;
import com.upc.trabajoarquitectura.respository.ProductoRepository;
import com.upc.trabajoarquitectura.respository.ProductoxSupermercadoRepository;
import com.upc.trabajoarquitectura.respository.SupermercadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ProductoxSupermercadoService implements IProductoxSupermercadoService {
    @Autowired
    private ProductoxSupermercadoRepository productoxSupermercadoRepository;
    @Autowired
    private SupermercadoRepository supermercadoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Transactional
    public List<ProductoxSupermercado> listarProductoxSupermercado () {
        return productoxSupermercadoRepository.findAll();
    }
    @Transactional
    public void registrarProductoxSupermercado(Long productoID, Long supermercadoID, Long descuentoID, double precio) {
        ProductoxSupermercado productoxSupermercado = productoxSupermercadoRepository.findByPrimaryKey_Producto_ProductoIDAndPrimaryKey_Supermercado_SupermercadoID(productoID, supermercadoID);
        LocalDate fecha = LocalDate.now();
        Descuento descuento = descuentoRepository.findById(descuentoID).orElse(null);  // Buscar el descuento
        if (productoxSupermercado != null && descuento != null) {
            productoxSupermercado.setFecha(fecha);
            productoxSupermercado.setPrecio(precio);
            productoxSupermercado.setDescuento(descuento);
            productoxSupermercadoRepository.save(productoxSupermercado);
        }else{
            ProductoxSupermercado nuevoProductoxSupermercado = new ProductoxSupermercado();
            nuevoProductoxSupermercado.setSupermercado(supermercadoRepository.findById(supermercadoID).get());
            nuevoProductoxSupermercado.setProducto(productoRepository.findById(productoID).get());
            nuevoProductoxSupermercado.setFecha(fecha);
            nuevoProductoxSupermercado.setPrecio(precio);
            nuevoProductoxSupermercado.setDescuento(descuento);

            productoxSupermercadoRepository.save(nuevoProductoxSupermercado);
        }
    }

    @Override
    public List<ProductoSupermercadoDTO> encontrarPrecioDeProductoPorSupermercado(Long idProduct) {
        return productoxSupermercadoRepository.findPricesProductsForEachSupermarket(idProduct);
    }

    @Override
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorDeProductos() {
        return productoxSupermercadoRepository.findMinPricesForProductsBySupermarket();
    }

    @Override
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioMenorDeProductosOrdenadoAsc() {
        return productoxSupermercadoRepository.findMinPricesForProductsBySupermarketOrderAsc();
    }

    @Override
    public List<PrecioProductosSupermercadoDTO> encontrarPrecioDeProductoEntrePrecios(double precioMin, double precioMax){
        return productoxSupermercadoRepository.findMinPricesForProductsBySupermarketBetweenPrices(precioMin, precioMax);
    }

}
