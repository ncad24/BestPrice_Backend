package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="categoria")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoriaID")
    private Long categoriaID;
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "productoID")
    @JsonBackReference("producto_categorias")
    private Producto producto;

}

