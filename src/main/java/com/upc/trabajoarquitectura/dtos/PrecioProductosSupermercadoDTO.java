package com.upc.trabajoarquitectura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PrecioProductosSupermercadoDTO {
    private Long idProducto;
    private String nombreProducto;
    private double precioProducto;
}
