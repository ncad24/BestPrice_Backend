package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upc.trabajoarquitectura.util.ProductsByListID;
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
        @AssociationOverride(name = "primaryKey.userList",
                joinColumns = @JoinColumn(name = "listId")),
        @AssociationOverride(name = "primaryKey.product",
                joinColumns = @JoinColumn(name = "productID"))
})
public class ProductsByList {
    @EmbeddedId
    private ProductsByListID primaryKey = new ProductsByListID();

    @Column(name = "dateAdded")
    private LocalDate dateAdded;

    @Transient
    @JsonManagedReference
    public UserList getUserList() {
        return primaryKey.getUserList();
    }

    @Transient
    @JsonManagedReference
    public Product getProduct() {
        return primaryKey.getProduct();
    }

    public void setUserList(UserList userList) {
        this.primaryKey.setUserList(userList);
    }

    public void setProduct(Product product) {
        this.primaryKey.setProduct(product);
    }
}