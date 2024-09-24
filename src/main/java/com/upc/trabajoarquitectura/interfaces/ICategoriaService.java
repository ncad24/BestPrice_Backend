package com.upc.trabajoarquitectura.interfaces;


import com.upc.trabajoarquitectura.entities.Categoria;

import java.util.List;

public interface ICategoriaService {
    public List<Categoria> listarCategorias();
    public Categoria registrarCategoria(Categoria categoria);
    public Categoria actualizarCategoria(Categoria categoria);
    public void eliminarCategoria(Long categoriaID) throws Exception;
}

