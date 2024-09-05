package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Categoria;
import com.upc.trabajoarquitectura.interfaces.ICategoriaService;
import com.upc.trabajoarquitectura.respository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria registrarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria actualizarCategoria(Categoria categoria){
        if (categoriaRepository.findById(categoria.getCategoriaID()).isPresent()){
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @Transactional
    public void eliminarCategoria(Long categoriaID) throws Exception {
        if (categoriaRepository.existsById(categoriaID)){
            categoriaRepository.deleteById(categoriaID);
        }else {
            throw new Exception("No se puede eliminar la categoria");
        }
    }
}
