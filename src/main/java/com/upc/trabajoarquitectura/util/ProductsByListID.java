package com.upc.trabajoarquitectura.util;

import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.UserList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
public class ProductsByListID{
    @ManyToOne(cascade = CascadeType.ALL)
    private UserList userList;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
