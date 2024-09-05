package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productoID")
    private Long productoID;
    private String nombre;
    private String descripcion;
    private double precio;

    @ManyToOne
    @JsonBackReference("producto_marcas")
    @JoinColumn(name = "marcaID")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "categoriaID")
    @JsonBackReference("producto_categorias")
    private Categoria categoria;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference("producto_supermercado")
    @JoinTable(
            name = "supermercado_producto",
            joinColumns = @JoinColumn(name = "productoID"),
            inverseJoinColumns = @JoinColumn(name = "supermercadoID")
    )
    private Set<Supermercado> supermercadoProductos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference("producto_distrito")
    @JoinTable(
            name = "distrito_producto",
            joinColumns = @JoinColumn(name = "productoID"),
            inverseJoinColumns = @JoinColumn(name = "distritoID")
    )
    private Set<Distrito> distritoProductos = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.producto", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductoxUsuario> productoxUsuarios = new HashSet<>();

}
