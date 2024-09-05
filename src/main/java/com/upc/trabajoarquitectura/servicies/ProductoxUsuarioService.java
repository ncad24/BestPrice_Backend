package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import com.upc.trabajoarquitectura.interfaces.IProductoxUsuarioService;
import com.upc.trabajoarquitectura.respository.ProductoxUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductoxUsuarioService implements IProductoxUsuarioService {
    @Autowired
    private ProductoxUsuarioRepository productoxUsuarioRepository;

    @Transactional
    public void registrarProductoxUsuario(Long productoID, Long usuarioID) {
        ProductoxUsuario productoxUsuario = productoxUsuarioRepository.findByProductoIdAndUsuarioId(productoID,usuarioID);
        LocalDate fecha = productoxUsuario.getFecha();
        if (productoxUsuario != null) {
            productoxUsuario.setFecha(fecha);
            productoxUsuarioRepository.save(productoxUsuario);
        }else{
            ProductoxUsuario nuevoProductoxUsuario = new ProductoxUsuario();
            nuevoProductoxUsuario.setFecha(fecha);
            productoxUsuarioRepository.save(nuevoProductoxUsuario);
        }
    }
}
