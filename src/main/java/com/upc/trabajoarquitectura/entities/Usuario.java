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
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioID")
    private Long usuarioID;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String nombreUsuario;
    private String contrasenia;
    @ManyToOne
    @JoinColumn (name = "rolID")
    private Rol rol;

    @OneToMany(mappedBy = "primaryKey.usuario", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductoxUsuario> productoxUsuario = new HashSet<>();
}
