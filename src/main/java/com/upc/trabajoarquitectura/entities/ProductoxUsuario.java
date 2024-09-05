package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="producto_usuario")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.usuario",
                joinColumns = @JoinColumn(name = "usuarioID")),
        @AssociationOverride(name = "primaryKey.producto",
                joinColumns = @JoinColumn(name = "productoID")) })
public class ProductoxUsuario {
    @EmbeddedId
    private ProductoxUsuarioID primaryKey = new ProductoxUsuarioID();

    @Column(name = "fecha")
    private LocalDate fecha;

    @Transient
    @JsonManagedReference
    public Usuario getUsuario() {
        return primaryKey.getUsuario();
    }

    @Transient
    @JsonManagedReference
    public Producto getProducto() {
        return primaryKey.getProducto();
    }

    public void setUsuario(Usuario usuario) {
        this.primaryKey.setUsuario(usuario);
    }

    public void setProducto(Producto producto) {
        this.primaryKey.setProducto(producto);
    }

}