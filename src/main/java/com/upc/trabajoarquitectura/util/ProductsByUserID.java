package com.upc.trabajoarquitectura.util;

import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.UserApp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductsByUserID {
    @ManyToOne(cascade = CascadeType.ALL)
    private UserApp userApp;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
