package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class DistritoDTO {

    private Long distritoID;
    private String nombre;

    private Set<Producto> distritoProductos = new HashSet<>();
}