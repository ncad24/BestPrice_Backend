package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario registrarUsuario(Usuario usuario);
    public Usuario actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Long usuarioID) throws Exception;
}