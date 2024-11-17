package com.upc.trabajoarquitectura.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;

    private String name;  // nombre personalizado de la lista, como "Favoritos", "Para más tarde", etc.

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    @JsonBackReference
    private UserApp userApp;

    @OneToMany(mappedBy = "primaryKey.userList", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ProductsByList> productsByList = new HashSet<>();
}
