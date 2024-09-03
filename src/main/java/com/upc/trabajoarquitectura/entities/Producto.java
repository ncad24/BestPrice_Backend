package com.upc.trabajoarquitectura.entities;

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

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference("producto_marcas")
    private List<Marca> marcas = new ArrayList();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference("producto_categorias")
    private  List<Categoria> categorias = new ArrayList();

    @ManyToMany (mappedBy = "supermercadoProductos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Supermercado> supermercados = new HashSet<>();
    @ManyToMany (mappedBy = "distritoProductos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Distrito> distritos = new HashSet<>();


}
