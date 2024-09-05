package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="supermercado")
public class Supermercado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supermercadoID")
    private Long supermercadoID;
    private String nombre;
    private String descripcion;

    @ManyToMany (mappedBy = "supermercadoProductos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("producto_supermercado")
    private Set<Producto> productos = new HashSet<>();



}

