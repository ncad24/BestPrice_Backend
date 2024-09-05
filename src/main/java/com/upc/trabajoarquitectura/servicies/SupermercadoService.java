package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Supermercado;
import com.upc.trabajoarquitectura.interfaces.ISupermercadoService;
import com.upc.trabajoarquitectura.respository.SupermercadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupermercadoService implements ISupermercadoService {
    @Autowired
    private SupermercadoRepository supermercadoRepository;
    @Transactional
    public List<Supermercado> listarSupermercados() {
        return supermercadoRepository.findAll();
    }

    @Transactional
    public Supermercado registrarSupermercado(Supermercado supermercado) {
        return supermercadoRepository.save(supermercado);
    }

    @Transactional
    public Supermercado actualizarSupermercado(Supermercado supermercado) {
        if (supermercadoRepository.findById(supermercado.getSupermercadoID()).isPresent()) {
            return supermercadoRepository.save(supermercado);
        }
        return null;
    }

    @Transactional
    public void eliminarSupermercado(Long supermercadoId) throws Exception {
        if (supermercadoRepository.existsById(supermercadoId)) {
            supermercadoRepository.deleteById(supermercadoId);
        }else{
            throw new Exception("No se puede eliminar el supermercado");
        }
    }
}
