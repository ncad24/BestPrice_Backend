package com.upc.trabajoarquitectura.entities;

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
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productoId")
    private Long productoID;
    private String nombre;
    private String descripcion;
    private double precio;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "categoria_Id")
    private Categoria categoria;

}
