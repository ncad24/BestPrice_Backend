package com.upc.trabajoarquitectura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "productoxusuario")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoxUsuario {
    @EmbeddedId
    private ProductoxUsuarioID id;
    private LocalDate fecha;
}
