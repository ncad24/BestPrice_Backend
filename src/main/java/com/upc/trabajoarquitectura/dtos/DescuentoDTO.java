package com.upc.trabajoarquitectura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DescuentoDTO {
    private Long descuentoID;
    private String nombre;
    private String descripcion;
    private BigDecimal porcentaje;
}
