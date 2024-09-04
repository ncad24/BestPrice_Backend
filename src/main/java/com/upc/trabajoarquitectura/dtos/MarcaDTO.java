package com.upc.trabajoarquitectura.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.upc.trabajoarquitectura.entities.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class MarcaDTO {
    private Long marcaID;
    private String nombre;

    private Producto producto;
}
