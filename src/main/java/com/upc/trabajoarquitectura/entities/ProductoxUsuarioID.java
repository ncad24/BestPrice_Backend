package com.upc.trabajoarquitectura.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductoxUsuarioID {
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.ALL)
    private Producto producto;
}
