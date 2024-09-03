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
    @Column(name="supermercado_id")
    private Long idSupermercad;
    private String nombre;
    private String descripcion;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "supermercado_productos",
            joinColumns = @JoinColumn(name = "supermercado_id"),
            inverseJoinColumns = @JoinColumn(name = "productos_id")
    )
    private Set<Producto> supermercadoProductos = new HashSet<>();

}

