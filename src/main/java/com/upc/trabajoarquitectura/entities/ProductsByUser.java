package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upc.trabajoarquitectura.util.ProductsByUserID;
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
        @AssociationOverride(name = "primaryKey.userApp",
                joinColumns = @JoinColumn(name = "userID")),
        @AssociationOverride(name = "primaryKey.product",
                joinColumns = @JoinColumn(name = "productID")) })
public class ProductsByUser {
    @EmbeddedId
    private ProductsByUserID primaryKey = new ProductsByUserID();

    @Column(name = "date")
    private LocalDate date;

    @Transient
    @JsonManagedReference
    public UserApp getUser() {
        return primaryKey.getUserApp();
    }

    @Transient
    @JsonManagedReference
    public Product getProduct() {
        return primaryKey.getProduct();
    }

    public void setUserApp(UserApp userApp) {
        this.primaryKey.setUserApp(userApp);
    }

    public void setProduct(Product product) {
        this.primaryKey.setProduct(product);
    }

}