package com.upc.trabajoarquitectura.respository;

import com.upc.trabajoarquitectura.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Querys
    //Se utiliza para el Spring Security
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
