package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Supermercado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supermercadoID")
    private Long supermercadoID;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "primaryKey.supermercado", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductoxSupermercado> productoxSupermercados = new HashSet<>();

}

