package com.upc.trabajoarquitectura.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductoxSupermercadoID {
    @ManyToOne(cascade = CascadeType.ALL)
    private Supermercado supermercado;
    @ManyToOne(cascade = CascadeType.ALL)
    private Producto producto;
}
