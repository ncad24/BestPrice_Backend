package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Rol;

public interface IRolService {
    public Rol registrarRol(Rol rol);
    public Rol actualizarRol(Rol rol);
    public void eliminarRol(Long rolID) throws Exception;
    public void asignarRol(Long rolID, Long usuarioID) throws Exception;
}