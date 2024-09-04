package com.upc.trabajoarquitectura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class RolDTO {
    private Long RolID;
    private String nombreRol;
    private String descripcion;
}
