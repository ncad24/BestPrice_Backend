package com.upc.trabajoarquitectura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoSupermercadoDTO {
    private String nombreSupermercado;
    private double precioProducto;
}
