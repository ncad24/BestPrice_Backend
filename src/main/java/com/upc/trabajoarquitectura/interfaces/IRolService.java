package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> listarRoles();
    public Rol registrarRol(Rol rol);
    public Rol actualizarRol(Rol rol);
    public void eliminarRol(Long rolID) throws Exception;
    public void asignarRol(Long rolID, Long usuarioID) throws Exception;
}