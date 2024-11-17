package com.upc.trabajoarquitectura.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productID")
    private Long productId;
    private String name;
    private String description;
    private String advertisement;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "brandID")
    //puede ser null en caso de que sea pan
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @OneToMany(mappedBy = "primaryKey.product", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductsBySupermarket> productsBySupermarket = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.product", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductsByList> productsByList = new HashSet<>();

    //@OneToMany(mappedBy = "primaryKey.product", cascade = CascadeType.ALL)
    //@JsonBackReference
    //private Set<ProductsByUser> productsByUser = new HashSet<>();

}
