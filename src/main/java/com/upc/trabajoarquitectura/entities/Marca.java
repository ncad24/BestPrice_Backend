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
@Table(name="marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="marcaID")
    private Long marcaID;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "productoID")
    @JsonBackReference("producto_marcas")
    private Producto producto;

}
