package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Rol;
import com.upc.trabajoarquitectura.entities.Usuario;
import com.upc.trabajoarquitectura.interfaces.IUsuarioService;
import com.upc.trabajoarquitectura.respository.RolRepository;
import com.upc.trabajoarquitectura.respository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    @Transactional
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getUsuarioID()).isPresent()) {
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @Transactional
    public void eliminarUsuario(Long usuarioID) throws Exception {
        if (usuarioRepository.existsById(usuarioID)) {
            usuarioRepository.deleteById(usuarioID);
        }
    }
}
