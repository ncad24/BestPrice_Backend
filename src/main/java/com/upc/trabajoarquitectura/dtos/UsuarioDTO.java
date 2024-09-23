package com.upc.trabajoarquitectura.dtos;

import com.upc.trabajoarquitectura.entities.Rol;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    private Long usuarioID;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String genero;
    private String nombreUsuario;
    private String contrasenia;
    private String rutaImagen;
    private Rol rol;
}
