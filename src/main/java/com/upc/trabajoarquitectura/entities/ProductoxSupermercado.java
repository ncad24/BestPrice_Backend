package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.supermercado",
                joinColumns = @JoinColumn(name = "supermercadoID")),
        @AssociationOverride(name = "primaryKey.producto",
                joinColumns = @JoinColumn(name = "productoID")) })
public class ProductoxSupermercado {
    @EmbeddedId
    private ProductoxSupermercadoID primaryKey = new ProductoxSupermercadoID();

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descuentoID")
    private Descuento descuento;

    @Transient
    @JsonManagedReference
    public Supermercado getSupermercado() {
        return primaryKey.getSupermercado();
    }

    @Transient
    @JsonManagedReference
    public Producto getProducto() {
        return primaryKey.getProducto();
    }

    public void setSupermercado(Supermercado supermercado) {
        this. primaryKey.setSupermercado(supermercado);
    }

    public void setProducto(Producto producto) {
        this.primaryKey.setProducto(producto);
    }

}
