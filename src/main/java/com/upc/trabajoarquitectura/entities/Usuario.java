package com.upc.trabajoarquitectura.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private char telefono;
    private String nombreUsuario;
    private String contrasenia;
    @ManyToOne
    @JoinColumn (name = "rolID")
    private Rol rol;

}
