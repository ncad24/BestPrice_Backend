package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.Descuento;
import com.upc.trabajoarquitectura.interfaces.IDescuentoService;
import com.upc.trabajoarquitectura.respository.DescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DescuentoService implements IDescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Transactional
    public List<Descuento> listarDescuentos() {
        return descuentoRepository.findAll();
    }

    @Transactional
    public Descuento registrarDescuento(Descuento descuento) {
        return descuentoRepository.save(descuento);
    }

    @Transactional
    public Descuento actualizarDescuento(Descuento descuento) {
        if (descuentoRepository.findById(descuento.getDescuentoID()).isPresent()) {
            return descuentoRepository.save(descuento);
        }
        return null;
    }

    @Override
    public void eliminarDescuento(Long descuentoID) throws Exception {
        if (descuentoRepository.existsById(descuentoID)) {
            descuentoRepository.deleteById(descuentoID);
        }else{
            throw new Exception("Descuento no se puede eliminar");
        }
    }
}
