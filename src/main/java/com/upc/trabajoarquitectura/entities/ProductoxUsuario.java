package com.upc.trabajoarquitectura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "productoxusuario")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoxUsuario {
    @Id
    @ManyToOne
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "productoID")
    private Producto producto;

    private LocalDate fecha;
}