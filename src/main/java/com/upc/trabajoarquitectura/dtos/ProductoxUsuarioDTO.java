package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Producto;
import com.upc.trabajoarquitectura.entities.Usuario;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductoxUsuarioDTO {
    private Usuario usuario;
    private Producto producto;

    private LocalDate fecha;
}
