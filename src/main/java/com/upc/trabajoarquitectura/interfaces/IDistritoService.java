package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Distrito;

import java.util.List;

public interface IDistritoService {
    public List<Distrito> listarDistritos();
    public Distrito registrarDistrito(Distrito distrito);
    public Distrito actualizarDistrito(Distrito distrito);
    public void eliminarDistrito(Long distritoId) throws Exception;
}
