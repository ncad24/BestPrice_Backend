package com.upc.trabajoarquitectura.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.upc.trabajoarquitectura.util.ProductsBySupermarketID;
import com.upc.trabajoarquitectura.util.ProductsByUserID;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userId;
    private String names;
    private String surnames;
    private String phoneNumber;
    private String email;
    private String gender;
    private String username;
    private String password;
    //esto es null
    private String imagePath;
    @ManyToOne
    @JoinColumn (name = "roleID")
    private Role role;

    //@OneToMany(mappedBy = "primaryKey.userApp", cascade = CascadeType.ALL)
    //@JsonBackReference
    //private Set<ProductsByUser> productsByUser = new HashSet<>();

}
