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
public class Supermarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supermarketID")
    private Long supermarketId;
    private String name;
    //esto puede ser null
    private String description;
    private String imagePath;

    @OneToMany(mappedBy = "primaryKey.supermarket", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductsBySupermarket> productsBySupermarket = new HashSet<>();

}

