package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Descuento;
import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.entities.Supermercado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductoxSupermercadoDTO {
    private Supermercado supermercado;
    private Producto producto;
    private double precio;
    private LocalDate fecha;
    private Descuento descuento;
}
