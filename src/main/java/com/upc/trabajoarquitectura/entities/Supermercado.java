package com.upc.trabajoarquitectura.entities;

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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "supermercado_producto",
            joinColumns = @JoinColumn(name = "supermercadoID"),
            inverseJoinColumns = @JoinColumn(name = "productoID")
    )
    private Set<Producto> supermercadoProductos = new HashSet<>();

}

