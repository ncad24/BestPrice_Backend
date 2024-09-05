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
@Table(name="distrito")

public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="distritoID")
    private Long distritoID;
    private String nombre;

    @ManyToMany (mappedBy = "distritoProductos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("producto_distrito")
    private Set<Producto> productos = new HashSet<>();
}
