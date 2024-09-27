package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface IProductoxUsuarioService {
    public List<ProductoxUsuario> listarProductoxUsuario ();
    public void registrarProductoxUsuario(Long productoID, Long usuarioID);
}
