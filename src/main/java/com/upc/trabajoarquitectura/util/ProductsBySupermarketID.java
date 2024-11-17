package com.upc.trabajoarquitectura.util;

import com.upc.trabajoarquitectura.entities.Product;
import com.upc.trabajoarquitectura.entities.Supermarket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductsBySupermarketID {
    @ManyToOne(cascade = CascadeType.ALL)
    private Supermarket supermarket;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
