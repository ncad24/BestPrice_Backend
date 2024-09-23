package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.Descuento;

import java.util.List;

public interface IDescuentoService {
    public List<Descuento> listarDescuentos();
    public Descuento registrarDescuento(Descuento descuento);
    public Descuento actualizarDescuento(Descuento descuento);
    public void eliminarDescuento(Long descuentoID) throws Exception;
}
