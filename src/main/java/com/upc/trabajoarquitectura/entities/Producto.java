package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productoID")
    private Long productoID;
    private String nombre;
    private String descripcion;
    private String rutaimagen;

    @ManyToOne
    @JoinColumn(name = "marcaID")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "categoriaID")
    private Categoria categoria;

    @OneToMany(mappedBy = "primaryKey.producto", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductoxSupermercado> productoxSupermercados = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.producto", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductoxUsuario> productoxUsuarios = new HashSet<>();

}
