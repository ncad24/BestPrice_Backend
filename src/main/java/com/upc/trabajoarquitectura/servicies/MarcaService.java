package com.upc.trabajoarquitectura.servicies;


import com.upc.trabajoarquitectura.entities.Marca;
import com.upc.trabajoarquitectura.interfaces.IMarcaService;
import com.upc.trabajoarquitectura.respository.MarcaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarcaService implements IMarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public List<Marca> listarMarca() {
        return marcaRepository.findAll();
    }

    @Transactional
    public Marca registrarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Transactional
    public Marca actualizarMarca(Marca marca) {
        if (marcaRepository.findById(marca.getMarcaID()).isPresent()) {
            return marcaRepository.save(marca);
        }
        return null;
    }

    @Transactional
    public void eliminarMarca(Long marcaId) throws Exception {
        if (marcaRepository.existsById(marcaId)) {
            marcaRepository.deleteById(marcaId);
        }else{
            throw new Exception("Marca no se puede eliminar");
        }
    }

}
