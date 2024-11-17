package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upc.trabajoarquitectura.util.ProductsBySupermarketID;
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
        @AssociationOverride(name = "primaryKey.supermarket",
                joinColumns = @JoinColumn(name = "supermarketID")),
        @AssociationOverride(name = "primaryKey.product",
                joinColumns = @JoinColumn(name = "productID")) })
public class ProductsBySupermarket {
    @EmbeddedId
    private ProductsBySupermarketID primaryKey = new ProductsBySupermarketID();

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private LocalDate date;

    @Transient
    @JsonManagedReference
    public Supermarket getSupermarket() {
        return primaryKey.getSupermarket();
    }

    @Transient
    @JsonManagedReference
    public Product getProduct() {
        return primaryKey.getProduct();
    }

    public void setSupermarket(Supermarket supermarket) {
        this. primaryKey.setSupermarket(supermarket);
    }

    public void setProduct(Product product) {
        this.primaryKey.setProduct(product);
    }

}
