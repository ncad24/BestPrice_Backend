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
@Table(name="distrito")

public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="distritoID")
    private Long distritoID;
    private String nombre;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "distrito_producto",
            joinColumns = @JoinColumn(name = "distritoID"),
            inverseJoinColumns = @JoinColumn(name = "productoID")
    )
    private Set<Producto> distritoProducto = new HashSet<>();
}
