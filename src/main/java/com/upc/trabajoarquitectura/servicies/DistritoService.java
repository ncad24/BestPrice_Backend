package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Distrito;
import com.upc.trabajoarquitectura.interfaces.ICategoriaService;
import com.upc.trabajoarquitectura.interfaces.IDistritoService;
import com.upc.trabajoarquitectura.respository.DistritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoService implements IDistritoService {
    @Autowired
    private DistritoRepository distritoRepository;

    @Transactional
    public List<Distrito> listarDistritos() {
        return distritoRepository.findAll();
    }

    @Transactional
    public Distrito registrarDistrito(Distrito distrito) {
        return distritoRepository.save(distrito);
    }

    @Transactional
    public Distrito actualizarDistrito(Distrito distrito) {
        if (distritoRepository.findById(distrito.getDistritoID()).isPresent()){
            return distritoRepository.save(distrito);
        }
        return null;
    }

    @Transactional
    public void eliminarDistrito(Long distritoId) throws Exception {
        if (distritoRepository.existsById(distritoId)) {
            distritoRepository.deleteById(distritoId);
        }else {
            throw new Exception("Distrito no se puede eliminar");
        }
    }
}
