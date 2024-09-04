package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Marca;

import java.util.List;

public interface IMarcaService {
    public List<Marca> listarMarca();
    public Marca registrarMarca(Marca marca);
    public Marca actualizarMarca(Marca marca);
    public void eliminarMarca(Long marcaId) throws Exception;
}
