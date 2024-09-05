package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.ProductoxUsuario;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface IProductoxUsuarioService {
    public void registrarProductoxUsuario(Long productoID, Long usuarioID);
}
