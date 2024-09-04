package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Supermercado;

import java.util.List;

public interface ISupermercadoService {
    public List<Supermercado> listarSupermercados();
    public Supermercado registrarSupermercado(Supermercado supermercado);
    public Supermercado actualizarSupermercado(Supermercado supermercado);
    public void eliminarSupermercado(Long supermercadoId) throws Exception;
}
